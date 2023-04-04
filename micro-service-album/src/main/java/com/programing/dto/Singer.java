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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Singer )) return false;
        return id != null && id.equals(((Singer) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
