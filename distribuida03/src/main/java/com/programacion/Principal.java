package com.programacion;

import com.programacion.servicios.HelloService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Principal {


    public static void main(String[] args) {

//        SeContainer contenedor = SeContainerInitializer.newInstance().initialize();
//        Instance<HelloService> service = contenedor.select(HelloService.class);
//
//        HelloService hs = service.get();
//        hs.rest();


        WeldContainer weldContainer = new Weld().initialize();
        HelloService service = weldContainer.instance().select(HelloService.class).get();
        service.rest();


    }

}
