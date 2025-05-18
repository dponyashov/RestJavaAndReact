package ru.dponyashov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "t_weapon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeaponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "weapon_name")
    String name;

    @Column(name = "weapon_description")
    String description;

    @ManyToMany(mappedBy = "weapons")
    @JsonIgnore
    private Collection<PersonEntity> persons;
}
