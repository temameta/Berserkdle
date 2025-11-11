package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends AbstractRepository<SpeciesEntity, Long> {
}
