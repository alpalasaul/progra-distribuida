package com.programacion.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.jboss.resteasy.spi.touri.MappedBy;

import java.util.Date;

@ToString
@Data
public class Album {

    private Integer id;
//    private Integer singer_id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date release_date;

}
