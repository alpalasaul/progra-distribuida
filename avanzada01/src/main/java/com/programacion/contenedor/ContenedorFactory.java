package com.programacion.contenedor;

public class ContenedorFactory {

    public static ContenedorAvanzada newInstance() {
        return new ContenedorAvanzadaImpl();
    }

}
