package ru.dponyashov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dponyashov.entity.ProfessionEntity;

@Repository
public interface ProfessionRepository extends JpaRepository<ProfessionEntity, Long> {
}
