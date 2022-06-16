package com.programacion;

import com.programacion.servicios.HelloService;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class Principal {


    public static void main(String[] args) {

        SeContainer contenedor = SeContainerInitializer.newInstance().initialize();
        Instance<HelloService> service = contenedor.select(HelloService.class);

        HelloService hs = service.get();
        hs.rest();

    }

}
