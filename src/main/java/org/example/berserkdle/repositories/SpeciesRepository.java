package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends AbstractRepository<SpeciesEntity> {
    @Query("SELECT name FROM SpeciesEntity ")
    List<String> getAllNames();
}
