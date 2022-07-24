package com.programacion.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@ToString
@Data
public class Singer {

    private Integer id;
    private String firsName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Set<Album> album;

}
