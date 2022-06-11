package com.programacion.config;

import com.programacion.servicios.Operaciones;
import com.programacion.servicios.OperacionesImpl;
import com.programacion.servicios.ServicioLog;
import com.programacion.servicios.ServicioLogImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.programacion.servicios")
public class AppConfig {

    @Bean // Equivalente a produces en CDI
    public ServicioLog createLog() {
        System.out.println("**********PRODUCTOR");
        return new ServicioLogImpl();
    }

    @Bean
    public Operaciones createOp() {
        System.out.println("********OP PROD");
        return new OperacionesImpl();
    }

}
