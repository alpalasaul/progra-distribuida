package com.programacion.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class HolaMundoDto implements Serializable {

    @Getter @Setter private String id;
    @Getter @Setter private String mensaje;
    @Getter @Setter private Date fecha;

}
