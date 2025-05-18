package ru.dponyashov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dponyashov.entity.RaceEntity;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Long> {
}
