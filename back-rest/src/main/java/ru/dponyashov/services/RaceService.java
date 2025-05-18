package ru.dponyashov.services;

import ru.dponyashov.dto.RaceDTO;

import java.util.Collection;

public interface RaceService {
    Collection<RaceDTO> getRaces();

    RaceDTO getRaceById(Long id);

    RaceDTO save(RaceDTO race);

    RaceDTO save(Long id, RaceDTO race);

    void deleteRaceById(Long id);
}
