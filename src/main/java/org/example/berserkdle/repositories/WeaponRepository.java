package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.WeaponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<WeaponEntity, Long> {
    WeaponEntity findByName(String name);
}
