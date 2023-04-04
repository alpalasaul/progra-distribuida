package com.programing.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
        @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.id = :id"),
})
@Table(name = "album")
public class Album implements Serializable {

    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="singer_id")
    private Singer singer;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate releaseDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album )) return false;
        return id != null && id.equals(((Album) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
