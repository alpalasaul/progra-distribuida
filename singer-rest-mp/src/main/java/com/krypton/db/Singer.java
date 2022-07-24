package com.krypton.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer {

    private Integer id;
    private String firsName;
    private String lastName;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Set<Album> album;

}
