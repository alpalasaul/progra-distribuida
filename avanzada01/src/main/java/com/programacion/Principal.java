package com.programacion;

import com.programacion.contenedor.ContenedorAvanzada;
import com.programacion.contenedor.ContenedorFactory;
import com.programacion.contenedor.anotaciones.MiComponente;
import com.programacion.servicios.CuentaBancaria;
import com.programacion.servicios.ManejeadorPersistencia;
import com.programacion.servicios.TransaccionBancaria;

public class Principal {

    public static void main(String[] args) {

        ContenedorAvanzada container = ContenedorFactory.newInstance();

        container.inicializar();

        TransaccionBancaria tb = container.buscar("transaccionBancaria", TransaccionBancaria.class);

        tb.realizarTransferencia("001", "002", 100.0f);

        ManejeadorPersistencia mp = container.buscar("manejeadorPersistencia", ManejeadorPersistencia.class);

        System.out.println(tb);
        System.out.println(mp);

        container.destruir();

    }

}
