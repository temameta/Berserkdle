package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByName(String name);
    @Query("SELECT name FROM Group")
    List<String> getAllNames();

    boolean existsByName(String name);
}
