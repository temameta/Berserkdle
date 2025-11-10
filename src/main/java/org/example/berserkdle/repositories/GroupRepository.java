package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByName(String name);
}
