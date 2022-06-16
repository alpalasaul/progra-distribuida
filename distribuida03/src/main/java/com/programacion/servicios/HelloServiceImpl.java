package com.programacion.servicios;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

//public class HelloService extends HttpServlet {


public class HelloServiceImpl implements HelloService {

    public static Object handleSumar(Request request, Response response) {

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

    @Override
    public void rest() {
        staticFiles.location("/public");
        port(8080);

        get("/sumar", HelloServiceImpl::handleSumar);

        get("/hello", (req, res) -> "Hello World");
    }

}
