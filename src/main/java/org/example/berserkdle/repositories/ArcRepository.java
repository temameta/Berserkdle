package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.ArcEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArcRepository extends JpaRepository<ArcEntity, Long> {
    public ArcEntity findByName(String name);
}
