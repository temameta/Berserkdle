package org.example.berserkdle.repositories;

import jdk.jfr.Registered;
import org.example.berserkdle.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByName(String name);
}
