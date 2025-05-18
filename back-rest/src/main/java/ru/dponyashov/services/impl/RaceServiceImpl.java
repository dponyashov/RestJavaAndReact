package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.RaceDTO;
import ru.dponyashov.entity.RaceEntity;
import ru.dponyashov.repositories.RaceRepository;
import ru.dponyashov.services.RaceService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    @Override
    public Collection<RaceDTO> getRaces() {
        return raceRepository.findAll().stream()
                .map(ent->RaceDTO.builder()
                        .name(ent.getName())
                        .id(ent.getId())
                        .build())
                .toList();
    }

    @Override
    public RaceDTO getRaceById(Long id) {
        return raceRepository.findById(id)
                .map(ent->RaceDTO.builder()
                        .name(ent.getName())
                        .id(ent.getId())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Race не найден!"));
    }

    @Override
    public RaceDTO save(Long id, RaceDTO race) {
        RaceDTO raceFromDB = getRaceById(id);
        if(raceFromDB == null){
            log.info("Не найден Race с id: {} для обновления", id);
        }

        race.setId(id);
        return save(race);
    }

    @Override
//    @Transactional
    public RaceDTO save(RaceDTO race) {
        RaceEntity ent = RaceEntity.builder()
                .name(race.getName())
                .id(race.getId())
                .build();

        RaceEntity savedEnt = raceRepository.save(ent);
        log.info("Записаны(обновлены) данные Race с id: {}", savedEnt.getId());
        return RaceDTO.builder()
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deleteRaceById(Long id) {
        raceRepository.deleteById(id);
        log.info("Удалены данные Race с id: {}", id);
    }
}
