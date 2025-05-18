package ru.dponyashov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_logo")
    private String logo;

    @Column(name = "person_lvl")
    private Integer lvl;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_description")
    private String description;

    @ManyToOne
    private RaceEntity race;

    @ManyToOne
    private ProfessionEntity profession;

    @ManyToMany
    @JoinTable(
            name = "t_person_weapon", //schema = "main_schema",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_weapon"))
    private Collection<WeaponEntity> weapons;

    @ManyToMany
    @JoinTable(
            name = "t_person_armor", //schema = "main_schema",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_armor"))
    private Collection<ArmorEntity> armors;

    @ManyToMany
    @JoinTable(
            name = "t_person_items", //schema = "main_schema",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_item"))
    private Collection<ItemEntity> items;
}
