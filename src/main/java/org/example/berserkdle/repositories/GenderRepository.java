package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    public GenderEntity findByName(String name);
}
