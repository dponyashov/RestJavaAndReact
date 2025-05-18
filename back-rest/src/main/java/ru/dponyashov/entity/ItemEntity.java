package ru.dponyashov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "t_item")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_description")
    private String description;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private Collection<PersonEntity> persons;
}
