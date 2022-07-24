package com.programacion.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HolaMundoImpl implements HolaMundo {

    @Override
    public String saludar(String nombre) {
        return "Hola " + nombre;
    }

}
