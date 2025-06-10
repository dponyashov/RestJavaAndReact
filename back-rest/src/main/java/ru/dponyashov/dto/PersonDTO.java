package ru.dponyashov.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    Long id;
    String logo;
    Integer lvl;
    String name;
    RaceDTO race;
    ProfessionDTO profession;
    String description;
//    Collection<WeaponDTO> weapons;
//    Collection<ArmorDTO> armors;
//    Collection<ItemDTO> items;
}
