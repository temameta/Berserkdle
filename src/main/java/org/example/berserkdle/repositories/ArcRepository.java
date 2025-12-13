package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.ArcEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArcRepository extends AbstractRepository<ArcEntity> {
    @Query("SELECT name FROM ArcEntity")
    List<String> getAllNames();
}
