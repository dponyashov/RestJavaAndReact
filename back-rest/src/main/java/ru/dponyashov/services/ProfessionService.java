package ru.dponyashov.services;

import ru.dponyashov.dto.ProfessionDTO;

import java.util.Collection;

public interface ProfessionService {
    Collection<ProfessionDTO> getProfessions();

    ProfessionDTO getProfessionById(Long id);

    ProfessionDTO save(ProfessionDTO profession);

    ProfessionDTO save(Long id, ProfessionDTO profession);

    void deleteProfessionById(Long id);
}
