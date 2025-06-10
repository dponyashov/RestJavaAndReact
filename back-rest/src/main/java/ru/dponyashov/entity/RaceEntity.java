package ru.dponyashov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "t_race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "race_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    private Collection<PersonEntity> persons;
}
