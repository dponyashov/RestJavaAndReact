package ru.dponyashov.services;

import ru.dponyashov.dto.ArmorDTO;
import ru.dponyashov.dto.WeaponDTO;

import java.util.Collection;

public interface WeaponService {
    Collection<WeaponDTO> getWeapons();

    WeaponDTO getWeaponById(Long id);

    WeaponDTO save(WeaponDTO weapon);

    WeaponDTO save(Long id, WeaponDTO weapon);

    void deleteWeaponById(Long id);
}
