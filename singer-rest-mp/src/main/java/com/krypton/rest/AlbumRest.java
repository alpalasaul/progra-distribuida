package com.krypton.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.krypton.db.Album;
import com.krypton.service.AlbumService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/album")
@ApplicationScoped
public class AlbumRest {

    @Inject
    AlbumService albumService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Album findById(@PathParam("id") Integer id) {
        return albumService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ObjectNode item) {
        try {
            albumService.insert(item);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg("Album guardado exitosamente"))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, ObjectNode item) {
        try {
            albumService.save(id, item);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg(String.format("Album: %s, actualizado exitosamente", id)))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
            albumService.delete(id);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(this.msg(ex.getMessage())).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(this.msg(String.format("Album: %s, eliminado exitosamente", id)))
                .build();
    }

    public ObjectNode msg(String message) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("msg", message);
        return res;
    }

}

