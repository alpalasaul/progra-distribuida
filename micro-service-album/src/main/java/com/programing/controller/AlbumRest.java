package com.programing.controller;

import com.programing.dto.Album;
import com.programing.service.AlbumService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/album")
@ApplicationScoped
public class AlbumRest {

    @Inject
    private AlbumService albumService;

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
    public Response insert(Album album) {
        try {
            albumService.insert(album);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity("Album guardado exitosamente")
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Album album) {
        try {
            albumService.save(album);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(String.format("Album: %s, actualizado exitosamente", album.getId()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Integer id) {
        try {
            albumService.delete(id);
        } catch (Exception ex) {
            return Response.status(Response
                    .Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(String.format("Album: %s, eliminado exitosamente", id))
                .build();
    }

}

