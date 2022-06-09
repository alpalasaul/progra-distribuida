package com.programacion;

import com.programacion.servicios.Operaciones;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Principal {

    // ejemplo con JSE
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize();
        Instance<Operaciones> obj = container.select(Operaciones.class);

        Operaciones servicio = obj.get();

        int resp = servicio.sumar(5, 4);
        System.out.printf("El resultado es: %d\n", resp);
    }



}
