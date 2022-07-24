package com.programacion.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.programacion.db.Singer;
import com.programacion.servicios.SingerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/singer")
@ApplicationScoped
public class SingerRest {

    // DI
    @Inject
    private SingerService singerService;

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
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg("Singer guardado exitosamente"))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Singer singer) {
        try {
            singerService.save(id, singer);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg(String.format("Singer: %s, actualizado exitosamente", id)))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
            singerService.delete(id);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg(String.format("Singer: %s, eliminado exitosamente", id)))
                .build();
    }

    public ObjectNode msg(String message) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("msg", message);
        return res;
    }

}
