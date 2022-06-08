package com.programacion.config;

import com.programacion.servicios.ServicioLog;
import com.programacion.servicios.ServicioLogImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped // indicamos que es un componente
public class AppConfig {

    @Produces // Producer method
    @ApplicationScoped
    public ServicioLog createLog() {
        System.out.println("**********PRODUCTOR");
        return new ServicioLogImpl();
    }

}
