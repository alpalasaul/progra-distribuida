package com.programacion.servicios;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped // es un ámbito (tenemos un componente registrado)
public class OperacionesImpl implements Operaciones {

    @Inject
    private ServicioLog logger;

    @Override
    public int sumar(int x, int y) {
        return x + y;
    }

    @PostConstruct
    void init() {
        logger.log("**********OperacionesImpl::init");
    }

    @PreDestroy
    void destroy() {
        logger.log("**********OperacionesImpl::destroy");
    }

}
