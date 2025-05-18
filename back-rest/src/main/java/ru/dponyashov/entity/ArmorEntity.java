package ru.dponyashov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "t_armor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArmorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "armor_name")
    private String name;

    @Column(name = "armor_description")
    private String description;

    @ManyToMany(mappedBy = "armors")
    @JsonIgnore
    private Collection<PersonEntity> persons;
}
