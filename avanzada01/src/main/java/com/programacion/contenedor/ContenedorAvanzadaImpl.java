package com.programacion.contenedor;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.common.reflect.ClassPath;
import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.contenedor.anotaciones.MiDependencia;
import com.programacion.contenedor.proxy.ComponenteProxyHandler;
import org.checkerframework.checker.fenum.qual.SwingTitleJustification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ContenedorAvanzadaImpl implements ContenedorAvanzada {

    private String packageScan = "com.programacion";

    protected Map<String, Object> componentesRegistrados = new HashMap<>();

    private static Logger _logger = LoggerFactory.getLogger(ContenedorAvanzadaImpl.class);

    @Override
    public void inicializar() {
        _logger.info("Inicializando");
        // buscar todas las clases en el programa
        try {
            ClassPath classPath = ClassPath.from(ContenedorAvanzadaImpl.class.getClassLoader());
            ImmutableSet<ClassPath.ClassInfo> clases = classPath.getTopLevelClassesRecursive(packageScan);
            clases.stream().forEach(s -> {
                String className = s.getName();
                Class<?> clase = null;

                try {
                    clase = Class.forName(className); // usar reflexión para obtener los meta datos

                    // 2. Identificar los que tiene una anotación en particular
                    MiComponente ann = clase.getAnnotation(MiComponente.class);
                    if (ann != null) {
                        _logger.info("Registrando {}===>{}", className, ann.nombre() + "\n");

                        // 3. Registrar como componentes
                        crearComponente(ann.nombre(), clase);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            });

            // 4. Procesar las dependencias
            procesarDependencias();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destruir() {
        _logger.info("Destruyendo");
    }

    @Override
    public <T> T buscar(String nombre, Class<T> cls) {
        Object obj = componentesRegistrados.get(nombre);
        if (obj == null) {
            throw new RuntimeException(String.format("Componente %s no encontrado", nombre));
        }
        return cls.cast(obj);
    }

    void procesarDependencias() {
        System.out.println("Procesando dependencias");
        // 1. Listar los componentes registrados
        for (String key : componentesRegistrados.keySet()) {
            System.out.printf("Procesando dependencias para %s\n", key);

            Object proxy = componentesRegistrados.get(key);

            ComponenteProxyHandler handler = (ComponenteProxyHandler) Proxy.getInvocationHandler(proxy);

            Object obj = handler.getTarget();

            // 2. Listar las variables de instancia
            Field[] fields = obj.getClass().getDeclaredFields();
            Stream.of(fields)
                    .forEach(s -> {

                        MiDependencia ann = s.getAnnotation(MiDependencia.class);
                        if (ann != null) {
                            System.out.printf("%s.%s===>%s\n", obj.getClass().getName(), s.getName(), ann.nombre());
                            Object dependenciaObj = componentesRegistrados.get(ann.nombre());

                            try {
                                s.setAccessible(true);
                                s.set(obj, dependenciaObj);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
        }
    }

    void crearComponente(String nombre, Class<?> cls) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object target = cls.getDeclaredConstructors()[0].newInstance();
        ComponenteProxyHandler handler = new ComponenteProxyHandler(target);
        Object proxy = Proxy.newProxyInstance(ContenedorAvanzadaImpl.class.getClassLoader(), cls.getInterfaces(), handler);

        componentesRegistrados.put(nombre, proxy);

//        Object obj = clase.getDeclaredConstructors()[0].newInstance();
//
//        componentesRegistrados.put(ann.nombre(), obj);
//
    }
}
