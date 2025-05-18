package ru.dponyashov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "t_profession")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profession_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="profession_id", referencedColumnName = "id")
    private Collection<PersonEntity> persons;
}
