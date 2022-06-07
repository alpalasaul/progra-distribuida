package com.krypton;

public class EstadoCuenta {

    private ManejadorPersistencia manejadorPersistencia;

    public EstadoCuenta() {
        manejadorPersistencia = new ManejadorPersistenciaImpl();
    }

    public String consultarEstado(String cuenta) {
        manejadorPersistencia.buscarCuenta(cuenta);

        // construir el estado
        return "";
    }

}
