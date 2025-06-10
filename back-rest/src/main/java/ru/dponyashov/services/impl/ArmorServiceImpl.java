package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.ArmorDTO;
import ru.dponyashov.entity.ArmorEntity;
import ru.dponyashov.repositories.ArmorRepository;
import ru.dponyashov.services.ArmorService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArmorServiceImpl implements ArmorService {
    private final ArmorRepository armorRepository;

    @Override
    public Collection<ArmorDTO> getArmors() {
        return armorRepository.findAll().stream()
                .map(ent -> ArmorDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .toList();
    }

    @Override
    public ArmorDTO getArmorById(Long id) {
        return armorRepository.findById(id)
                .map(ent -> ArmorDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Armor не найден!"));
    }

    @Override
    public ArmorDTO save(Long id, ArmorDTO armor) {

        ArmorDTO armorFromDB = getArmorById(id);
        if (armorFromDB == null) {
            log.info("Не найден Armor с id: {} для обновления", id);
        }

        armor.setId(id);
        return save(armor);
    }

    @Override
//    @Transactional
    public ArmorDTO save(ArmorDTO armor) {
        ArmorEntity ent = ArmorEntity.builder()
                .description(armor.getDescription())
                .name(armor.getName())
                .id(armor.getId())
                .build();

        ArmorEntity savedEnt = armorRepository.save(ent);
        log.info("Записаны(обновлены) данные Armor с id: {}", savedEnt.getId());
        return ArmorDTO.builder()
                .description(savedEnt.getDescription())
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deleteArmorById(Long id) {
        armorRepository.deleteById(id);
        log.info("Удалены данные Armor с id: {}", id);
    }
}
