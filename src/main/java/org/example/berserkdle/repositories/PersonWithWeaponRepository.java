package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.PersonWithWeaponEntity;
import org.example.berserkdle.entities.PersonWithWeaponId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonWithWeaponRepository extends JpaRepository<PersonWithWeaponEntity, PersonWithWeaponId> {
    List<PersonWithWeaponEntity> findAllByPerson_Id(Long personId);
    List<PersonWithWeaponEntity> findAllByPerson_Name(String name);
}
