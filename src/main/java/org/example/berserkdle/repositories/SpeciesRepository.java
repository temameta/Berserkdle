package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Long> {
    SpeciesEntity findByName(String name);
}
