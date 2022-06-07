package com.krypton;

public class ManejadorPersistenciaNosqlImpl implements ManejadorPersistencia {

    @Override
    public CuentaBancaria buscarCuenta(String numero) {
        System.out.printf("buscarCuenta NOSQL(%s)\n", numero);
        return new CuentaBancaria(numero);
    }

}
