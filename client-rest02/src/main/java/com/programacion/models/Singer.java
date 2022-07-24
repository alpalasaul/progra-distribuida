package com.programacion.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@ToString
@Data
public class Singer {

    private Integer id;
    private String firsName;
    private String lastName;
    private Date birthDate;
    private Set<Album> album;

}
