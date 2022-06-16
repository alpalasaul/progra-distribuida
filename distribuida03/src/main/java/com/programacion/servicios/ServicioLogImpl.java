package com.programacion.servicios;

//@ApplicationScoped // se convierte en un componente [managed bean]
public class ServicioLogImpl implements ServicioLog {

    @Override
    public void log(String msg) {
        System.out.println("LOG: " + msg);
    }

}
