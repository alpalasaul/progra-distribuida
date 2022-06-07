package com.programacion.contenedor;

public interface ContenedorAvanzada {

    void inicializar();

    void destruir();

    <T> T buscar(String nombre, Class<T> cls);

}
