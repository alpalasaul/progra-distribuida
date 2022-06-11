package com.programacion.config;

import com.programacion.servicios.ServicioLog;
import com.programacion.servicios.ServicioLogImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.programacion.servicios")
public class AppConfig {

    @Bean // Equivalente a producer en CDI
    public ServicioLog createLog() {
        System.out.println("**********PRODUCTOR");
        return new ServicioLogImpl();
    }

//    @Bean
//    public Operaciones createOp() {
//        System.out.println("********OP PROD");
//        return new OperacionesImpl();
//    }

}
