package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends AbstractRepository<PersonEntity> {
    @Query("SELECT name FROM PersonEntity")
    List<String> getAllNames();
}
