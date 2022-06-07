package com.krypton;

public class ManejadorPersistenciaXyzImpl implements ManejadorPersistencia {

    @Override
    public CuentaBancaria buscarCuenta(String numero) {
        System.out.println("**********Buscando cuentaXyx: " + numero);
        return new CuentaBancaria(numero);
    }

}
