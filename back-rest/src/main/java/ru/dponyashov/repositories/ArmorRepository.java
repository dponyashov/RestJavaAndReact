package ru.dponyashov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dponyashov.entity.ArmorEntity;

@Repository
public interface ArmorRepository extends JpaRepository<ArmorEntity, Long> {
}
