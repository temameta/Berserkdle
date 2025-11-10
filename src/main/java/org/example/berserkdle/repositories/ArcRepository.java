package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.ArcEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArcRepository extends JpaRepository<ArcEntity, Long> {
    ArcEntity findByName(String name);
}
