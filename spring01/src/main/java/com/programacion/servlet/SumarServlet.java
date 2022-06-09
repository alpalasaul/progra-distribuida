package com.programacion.servlet;

import com.programacion.servicios.Operaciones;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/sumar")
//public class SumarServlet extends HttpServlet {
//
//    @Autowired
//    private Operaciones servicio;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int ret = servicio.sumar(5, 7);
//        PrintWriter pw = resp.getWriter();
//
//        resp.setContentType("text/html");
//
//        pw.printf("La suma es: %d", ret);
//    }
//
//}
