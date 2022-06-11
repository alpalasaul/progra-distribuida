package com.programacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class OperacionesImpl implements Operaciones, InitializingBean, DisposableBean {
//@Component
public class OperacionesImpl implements Operaciones { // se usa post y pre importando con dependencias

    @Autowired
    private ServicioLog logger;

    @Override
    public int sumar(int x, int y) {
        logger.log("********OperacionesImpl::sumar");
        return x + y;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception { // equivalente al init
//        logger.log("**********OperacionesImpl::init");
//    }
//
//    @Override
//    public void destroy() throws Exception { // equivalente al destroy
//        logger.log("**********OperacionesImpl::destroy");
//    }

    @PostConstruct
    void init() {
        logger.log("**********OperacionesImpl::init");
    }

    @PreDestroy
    void destroy() {
        logger.log("**********OperacionesImpl::destroy");
    }

}

