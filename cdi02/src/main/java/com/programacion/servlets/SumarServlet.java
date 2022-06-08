package com.programacion.servlets;

import com.programacion.servicios.Operaciones;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/sumar")
public class SumarServlet extends HttpServlet {

    @Inject
    private Operaciones servicio;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int ret = servicio.sumar(5, 7);
        PrintWriter pw = resp.getWriter();

        resp.setContentType("text/html");

        pw.printf("La suma es: %d", ret);
    }
}
