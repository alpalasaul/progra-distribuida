package com.krypton;

public class ManejadorPersistenciaLogImpl implements ManejadorPersistencia {

    private final ManejadorPersistenciaImpl impl = new ManejadorPersistenciaImpl();

    @Override
    public CuentaBancaria buscarCuenta(String numero) {
        System.out.println("********Buscando cuenta: " + numero);
        return impl.buscarCuenta(numero);
    }

}
