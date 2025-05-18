package ru.dponyashov.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArmorDTO {
    Long id;
    String name;
    String description;
}
