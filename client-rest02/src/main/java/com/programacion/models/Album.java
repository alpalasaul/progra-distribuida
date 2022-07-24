package com.programacion.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class Album {

    private Integer id;
//    private Integer singer_id;
    private String title;
    private Date release_date;

}
