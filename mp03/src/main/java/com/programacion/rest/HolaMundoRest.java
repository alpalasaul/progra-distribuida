package com.programacion.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HolaMundoRest {

    @GetMapping("/hola")
    public String hola() {
        return "Hola " + LocalDateTime.now();
    }

}
