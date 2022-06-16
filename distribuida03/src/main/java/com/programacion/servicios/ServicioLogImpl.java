package com.programacion.servicios;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // se convierte en un componente [managed bean]
public class ServicioLogImpl implements ServicioLog {

    @Override
    public void log(String msg) {
        System.out.println("LOG: " + msg);
    }

}
