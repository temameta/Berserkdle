package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends AbstractRepository<GroupEntity> {
    @Query("SELECT name FROM GroupEntity ")
    List<String> getAllNames();
}
