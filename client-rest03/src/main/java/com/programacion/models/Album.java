package com.programacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Data
public class Album {

    private Integer id;
    @JsonProperty
    private Integer singer_id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date release_date;

}
