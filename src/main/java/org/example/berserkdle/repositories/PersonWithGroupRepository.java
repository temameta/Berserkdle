package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.PersonWithGroupEntity;
import org.example.berserkdle.entities.PersonWithGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonWithGroupRepository extends JpaRepository<PersonWithGroupEntity, PersonWithGroupId> {
    List<PersonWithGroupEntity> findAllByPerson_Id(Long personId);
    List<PersonWithGroupEntity> findAllByPerson_Name(String personName);
}
