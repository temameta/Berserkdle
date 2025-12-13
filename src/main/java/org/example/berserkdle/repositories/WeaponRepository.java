package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.WeaponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends AbstractRepository<WeaponEntity> {
    @Query("SELECT name FROM WeaponEntity ")
    List<String> getAllNames();
}
