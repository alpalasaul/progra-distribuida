package com.programacion.servlet;

import com.programacion.config.AppConfig;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextLoaderListener extends ContextLoaderListener {

    public MyContextLoaderListener() {
        super(getWebApplicationContext());
    }

    private static WebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        return context;
    }
}