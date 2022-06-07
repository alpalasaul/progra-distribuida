package com.programacion.servicios;

import com.programacion.contenedor.anotaciones.MiComponente;

@MiComponente(nombre = "manejeadorPersistencia")
public class ManejeadorPersistenciaImpl implements ManejeadorPersistencia {

    @Override
    public CuentaBancaria buscarCuenta(String numero) {
        return new CuentaBancaria();
    }

}
