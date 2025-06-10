package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.ProfessionDTO;
import ru.dponyashov.entity.ProfessionEntity;
import ru.dponyashov.repositories.ProfessionRepository;
import ru.dponyashov.services.ProfessionService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProfessionServiceImpl implements ProfessionService {
    private final ProfessionRepository professionRepository;

    @Override
    public Collection<ProfessionDTO> getProfessions() {
        return professionRepository.findAll().stream()
                .map(ent -> ProfessionDTO.builder()
                        .name(ent.getName())
                        .id(ent.getId())
                        .build())
                .toList();
    }

    @Override
    public ProfessionDTO getProfessionById(Long id) {
        return professionRepository.findById(id)
                .map(ent -> ProfessionDTO.builder()
                        .name(ent.getName())
                        .id(ent.getId())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Profession не найден!"));
    }

    @Override
    public ProfessionDTO save(Long id, ProfessionDTO profession) {
        ProfessionDTO professionFromDB = getProfessionById(id);
        if (professionFromDB == null) {
            log.info("Не найден Profession с id: {} для обновления", id);
        }

        profession.setId(id);
        return save(profession);
    }

    @Override
//    @Transactional
    public ProfessionDTO save(ProfessionDTO profession) {
        ProfessionEntity ent = ProfessionEntity.builder()
                .name(profession.getName())
                .id(profession.getId())
                .build();

        ProfessionEntity savedEnt = professionRepository.save(ent);
        log.info("Записаны(обновлены) данные Profession с id: {}", savedEnt.getId());
        return ProfessionDTO.builder()
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deleteProfessionById(Long id) {
        professionRepository.deleteById(id);
        log.info("Удалены данные Profession с id: {}", id);
    }
}
