package com.programacion.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/hola")
public class HolaMundoRest {

    private HolaMundoDao holaMundoDao = new HolaMundoDaoImpl();

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public HolaMundoDto holaMundo(@PathParam("nombre") String nombre) {
        HolaMundoDto ret = new HolaMundoDto();
        ret.setMensaje(String.format("hola mundo %s", nombre));
        ret.setFecha(new Date());
        return ret;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<HolaMundoDto> holaMundoDaoList = holaMundoDao.findAll();
        return Response.status(Response.Status.OK)
                .entity(holaMundoDaoList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HolaMundoDto holaMundoDTO) {
        holaMundoDao.save(holaMundoDTO);
        return Response.status(Response.Status.CREATED)
                .entity(this.response("Nuevo registro creado"))
                .build();
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, HolaMundoDto holaMundoDTO) {
        var res = holaMundoDao.update(id, holaMundoDTO);
        return res
                ?
                Response.status(Response.Status.OK)
                        .entity(this.response(String.format("El registro %s ha sido actualizado", id)))
                        .build()
                :
                Response.status(Response.Status.NOT_FOUND)
                        .entity(this.response(String.format("Registro %s no encontrado", id)))
                        .build();
    }

    public ObjectNode response(String msg) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        res.put("msg", msg);
        return res;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
        var res = holaMundoDao.delete(id);
        return res
                ?
                Response.status(Response.Status.OK)
                        .entity(this.response(String.format("El registro %s ha sido eliminado", id)))
                        .build()
                :
                Response.status(Response.Status.NOT_FOUND)
                        .entity(this.response(String.format("Registro %s no encontrado", id)))
                        .build();
    }


}
