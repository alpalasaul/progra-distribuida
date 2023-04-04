package com.programing.controller;

import com.programing.dto.Singer;
import com.programing.service.SingerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/singer")
@ApplicationScoped
public class SingerRest {

    @Inject private SingerService singerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Singer> findAll() {
        return singerService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Singer findById(@PathParam("id") Integer id) {
        return singerService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Singer singer) {
        try {
            singerService.insert(singer);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity("Singer guardado exitosamente")
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Singer singer) {
        try {
            singerService.save(singer);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(String.format("Singer: %s, actualizado exitosamente", singer.getId()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Integer id) {
        try {
            singerService.delete(id);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(String.format("Singer: %s, eliminado exitosamente", id))
                .build();
    }

}
