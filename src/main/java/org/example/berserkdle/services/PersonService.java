package org.example.berserkdle.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.*;
import org.example.berserkdle.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;
    private PersonWithGroupRepository personWithGroupRepository;
    private GroupRepository groupRepository;
    private PersonWithWeaponRepository personWithWeaponRepository;
    private WeaponRepository weaponRepository;
    private SpeciesRepository speciesRepository;
    private GenderRepository genderRepository;
    private ArcRepository arcRepository;

    public List<PersonDTO> findAll() {
        return toPersonDTO(personRepository.findAll());
    }

    public PersonDTO findById(Long id) {
        return toPersonDTO(personRepository.findById(id));
    }

    public PersonDTO findByName(String name) {
        return toPersonDTO(personRepository.findByName(name));
    }

    private PersonDTO toPersonDTO(Optional<PersonEntity> optionalPersonEntity) {
        if (optionalPersonEntity.isPresent())
            return toPersonDTO(optionalPersonEntity.get());
        return null;
    }

    public void save(PersonDTO personDTO) {
        personRepository.save(toPersonEntity(personDTO));
    }

    public void save(List<PersonDTO> personDTOs) {
        for (PersonDTO personDTO : personDTOs) {
            save(personDTO);
        }
    }

    public PersonEntity toPersonEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();

        personEntity.setName(personDTO.getName());
        personEntity.setSpecies(speciesRepository.findByName(personDTO.getSpecies()));
        personEntity.setGender(genderRepository.findByName(personDTO.getGender()));
        personEntity.setFirstArc(arcRepository.findByName(personDTO.getFirstArc()));

        if (personRepository.findByName(personDTO.getName()) != null) {
            personEntity.setGroups(personWithGroupRepository.findAllByPerson_Name(personDTO.getName()));
            personEntity.setWeapons(personWithWeaponRepository.findAllByPerson_Name(personDTO.getName()));
        }
        else {
            for (String weaponName : personDTO.getWeapons()) {
                WeaponEntity weaponEntity = weaponRepository.findByName(weaponName);
                personEntity.addWeapon(new PersonWithWeaponEntity(new PersonWithWeaponId(personDTO.getId(), weaponEntity.getId()), personEntity, weaponEntity));
            }
            for (String groupName : personDTO.getGroups()) {
                GroupEntity groupEntity = groupRepository.findByName(groupName);
                personEntity.addGroup(new PersonWithGroupEntity(new PersonWithGroupId(personDTO.getId(), groupEntity.getId()), personEntity, groupEntity));
            }
        }
        return personEntity;
    }

    public List<PersonEntity> toPersonEntity(List<PersonDTO> personDTOs) {
        List<PersonEntity> personEntities = new ArrayList<>();
        for (PersonDTO personDTO : personDTOs) {
            personEntities.add(toPersonEntity(personDTO));
        }
        return personEntities;
    }

    public PersonDTO toPersonDTO(PersonEntity personEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(personEntity.getName());
        personDTO.setGender(personEntity.getGender().getName());
        personDTO.setFirstArc(personEntity.getFirstArc().getName());
        personDTO.setSpecies(personEntity.getSpecies().getName());
        for (PersonWithGroupEntity personWithGroupEntity : personWithGroupRepository.findAllByPerson_Id(personEntity.getId())) {
            personDTO.addGroup(groupRepository.findById(personWithGroupEntity.getGroup().getId()).get().getName());
        }
        for (PersonWithWeaponEntity personWithWeaponEntity : personWithWeaponRepository.findAllByPerson_Id(personEntity.getId())) {
            personDTO.addWeapon(groupRepository.findById(personWithWeaponEntity.getWeapon().getId()).get().getName());
        }
        return personDTO;
    }

    public List<PersonDTO> toPersonDTO(List<PersonEntity> personEntities) {
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            personDTOs.add(toPersonDTO(personEntity));
        }
        return personDTOs;
    }
}
