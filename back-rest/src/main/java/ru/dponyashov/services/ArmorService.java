package ru.dponyashov.services;

import ru.dponyashov.dto.ArmorDTO;

import java.util.Collection;

public interface ArmorService {
    Collection<ArmorDTO> getArmors();

    ArmorDTO getArmorById(Long id);

    ArmorDTO save(ArmorDTO armor);

    ArmorDTO save(Long id, ArmorDTO armor);

    void deleteArmorById(Long id);
}
