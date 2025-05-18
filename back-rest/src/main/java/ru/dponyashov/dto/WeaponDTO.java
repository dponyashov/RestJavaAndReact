package ru.dponyashov.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeaponDTO {
    Long id;
    String name;
    String description;
}
