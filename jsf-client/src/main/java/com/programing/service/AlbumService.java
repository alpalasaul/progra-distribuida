package com.programing.service;

import com.programing.model.Album;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://mp-album:9090/micro-service-album/api/v1")
@Path("/album")
public interface AlbumService extends AutoCloseable{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Album> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Album findOne(@PathParam("id") Integer id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(Album album);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(Album album);

    @DELETE
    Response delete(Integer id);
}
