package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends AbstractRepository<GenderEntity> {
}
