package com.programacion;

import com.programacion.servicios.HelloService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Principal {


    public static void main(String[] args) {

        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        HelloService service = weldContainer.select(HelloService.class).get();
        service.rest();

    }

}
