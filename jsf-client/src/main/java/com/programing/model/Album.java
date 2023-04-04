package com.programing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Album implements Serializable {

    private static final long serialVersionUID = 10L;

    private Integer id;
    private Singer singer;
    private String title;
    private LocalDate releaseDate;

}
