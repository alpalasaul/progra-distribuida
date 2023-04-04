package com.programing.service;

import com.programing.model.Singer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://mp-singer:9090/micro-service-singer/api/v1")
@Path("/singer")
public interface SingerService extends AutoCloseable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Singer> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findOne(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(Singer singer);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(Singer singer);

    @DELETE
    Response delete(Integer id);
}