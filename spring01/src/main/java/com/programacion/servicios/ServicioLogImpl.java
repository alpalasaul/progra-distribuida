package com.programacion.servicios;

import org.springframework.stereotype.Component;

//@ApplicationScoped // se convierte en un componente [managed bean]
@Component
public class ServicioLogImpl implements ServicioLog {

    @Override
    public void log(String msg) {
        System.out.println("LOG: " + msg);
    }

}
