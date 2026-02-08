package org.example.berserkdle.repositories;

import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    void deleteByName(String name);
    @Query("SELECT name FROM Person")
    List<String> getAllNames();

    Person findByName(String name);

    boolean existsByName(String name);
}
