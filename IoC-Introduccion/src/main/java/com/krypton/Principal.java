package com.krypton;

public class Principal {

    public static void main(String[] args) {

        TransaccionBancaria tb = new TransaccionBancaria();

        ManejadorPersistencia mp = new ManejadorPersistenciaXyzImpl();
        tb.setManejadorPersistencia(mp);

        tb.realizarTransferencia("001", "002", 10.0f);

        TransaccionBancaria tb2 = new TransaccionBancaria();
        tb2.setManejadorPersistencia(mp);

        TransaccionBancaria tb3 = new TransaccionBancaria();
        tb3.setManejadorPersistencia(mp);


    }

}
