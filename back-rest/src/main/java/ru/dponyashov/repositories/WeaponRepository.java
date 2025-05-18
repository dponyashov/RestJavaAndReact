package ru.dponyashov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dponyashov.entity.WeaponEntity;

@Repository
public interface WeaponRepository extends JpaRepository<WeaponEntity, Long> {
}
