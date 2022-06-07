package com.programacion.contenedor.anotaciones;

import jdk.jfr.Name;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MiDependencia {

    String nombre();

}
