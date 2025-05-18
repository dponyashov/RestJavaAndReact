package ru.dponyashov.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    Long id;
    String name;
    String description;
}
