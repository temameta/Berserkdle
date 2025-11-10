package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.PersonWithGroupEntity;
import org.example.berserkdle.entities.PersonWithGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonWithGroupRepository extends JpaRepository<PersonWithGroupEntity, PersonWithGroupId> {
    List<PersonWithGroupEntity> findAllByPerson_Id(Long personId);
    List<PersonWithGroupEntity> findAllByPerson_Name(String personName);
}
