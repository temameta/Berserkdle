package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByName(String name);
}
