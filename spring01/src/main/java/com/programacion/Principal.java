package com.programacion;

import com.programacion.config.AppConfig;
import com.programacion.servicios.Operaciones;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Principal {

    public static void main(String[] args) {

        // crear la instancia del contenedor
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // buscar un componente
        Operaciones servicio = context.getBean(Operaciones.class);
        System.out.println(servicio);

        // invocar al método
        int resp = servicio.sumar(5, 6);
        System.out.printf("El resultado es: %d\n", resp);

        // cerrar el contenedor
        context.close();

    }

}
