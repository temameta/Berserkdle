package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends AbstractRepository<GenderEntity> {
    @Query("SELECT name FROM GenderEntity ")
    List<String> getAllNames();
}
