package com.krypton;

public class CuentaBancaria {

    String numero = "";

    public CuentaBancaria(String numero) {
        this.numero = numero;
    }

    public void deposito(float monto) {
        System.out.printf("deposito(%.2f)\n", monto);
    }

    public void retiro(float monto) {
        System.out.printf("retiro(%.2f)\n", monto);
    }

}
