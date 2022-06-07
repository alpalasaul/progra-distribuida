package com.programacion.contenedor.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ComponenteProxyHandler implements InvocationHandler {

    private Object target;

    public ComponenteProxyHandler(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return this.target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long inicio = System.nanoTime();
        Object ret = method.invoke(target, args);
        long tiempo = System.nanoTime() - inicio;
        System.out.printf("*****método '%s', tiempo=%d\n", method.getName(), tiempo);
        return ret;
    }

}
