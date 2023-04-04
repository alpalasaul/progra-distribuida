package com.programing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Singer implements Serializable {

    private static final long serialVersionUID = 10L;

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Album> album;


}
