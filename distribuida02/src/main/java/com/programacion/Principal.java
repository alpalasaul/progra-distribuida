package com.programacion;

import spark.Request;
import spark.Response;
import spark.Route;

import javax.sql.rowset.spi.TransactionalWriter;

import static spark.Spark.*;

public class Principal {

    static public Object handleSumar(Request request, Response response) {
//        String x = request.params("x");
//        String y = request.params("y");

        String x = request.queryParams("x");
        String y = request.queryParams("y");

        Integer ret = Integer.valueOf(x) + Integer.valueOf(y);

        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<body>");
        sb.append("<b>La suma es <b/>").append(ret);
        sb.append("</html>");
        sb.append("</head>");
        sb.append("</body>");

        return sb.toString();
    }

    public static void main(String[] args) {

//        Route r1 = new Route() {
//            @Override
//            public Object handle(Request request, Response response) throws Exception {
//                return "Hola mundo";
//            }
//        };

//        get("/hello1", r1);

        staticFiles.location("/public");
        port(8080);

        get("/sumar", Principal::handleSumar);

        get("/hello", (req, res) -> "Hello World");
    }

}
