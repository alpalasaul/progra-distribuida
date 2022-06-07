package com.programacion.servicios;

import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.contenedor.anotaciones.MiDependencia;

@MiComponente(nombre = "transaccionBancaria")
public class TransaccionBancariaImpl implements TransaccionBancaria {

    @MiDependencia(nombre = "manejeadorPersistencia")
    protected ManejeadorPersistencia mp;

    @Override
    public void realizarTransferencia(String cuenta1, String cuenta2, float monto) {

        CuentaBancaria c1 = mp.buscarCuenta(cuenta1);
        CuentaBancaria c2 = mp.buscarCuenta(cuenta2);

        c1.deposito(monto);
        c2.retiro(monto);
    }

}
