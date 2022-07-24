package com.programacion.rest;

import com.programacion.service.HolaMundo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HolaMundoRest {

    @Inject
    private HolaMundo holaMundo;

    @GET
    @Path("/hola")
    public String hola() {
//        return "hola" + LocalDateTime.now();
        return holaMundo.saludar("dev");
    }

}
