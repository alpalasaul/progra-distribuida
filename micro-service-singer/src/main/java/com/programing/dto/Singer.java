package com.programing.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = "Singer.findAll", query = "SELECT s FROM Singer s"),
        @NamedQuery(name = "Singer.findById", query = "SELECT s FROM Singer s WHERE s.id = :id"),
})
@Table(name = "singer")
public class Singer implements Serializable {

    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonbDateFormat("yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private LocalDate birthDate;

    //    @OneToMany(
//            mappedBy = "singer",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="singer_id", nullable=false)
    private Set<Album> album;


}
