package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.WeaponDTO;
import ru.dponyashov.entity.WeaponEntity;
import ru.dponyashov.repositories.WeaponRepository;
import ru.dponyashov.services.WeaponService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class WeaponServiceImpl implements WeaponService {
    private final WeaponRepository weaponRepository;

    @Override
    public Collection<WeaponDTO> getWeapons() {
        return weaponRepository.findAll().stream()
                .map(ent->WeaponDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .toList();
    }

    @Override
    public WeaponDTO getWeaponById(Long id) {
        return weaponRepository.findById(id)
                .map(ent->WeaponDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Weapon не найден!"));
    }

    @Override
    public WeaponDTO save(Long id, WeaponDTO weapon) {

        WeaponDTO weaponFromDB = getWeaponById(id);
        if(weaponFromDB == null){
            log.info("Не найден Weapon с id: {} для обновления", id);
        }

        weapon.setId(id);
        return save(weapon);
    }

    @Override
//    @Transactional
    public WeaponDTO save(WeaponDTO weapon) {
        WeaponEntity ent = WeaponEntity.builder()
                .description(weapon.getDescription())
                .name(weapon.getName())
                .id(weapon.getId())
                .build();

        WeaponEntity savedEnt = weaponRepository.save(ent);
        log.info("Записаны(обновлены) данные Weapon с id: {}", savedEnt.getId());
        return WeaponDTO.builder()
                .description(savedEnt.getDescription())
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deleteWeaponById(Long id) {
        weaponRepository.deleteById(id);
        log.info("Удалены данные Weapon с id: {}", id);
    }
}
