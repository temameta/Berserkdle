package org.example.berserkdle.services;

import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.*;
import org.example.berserkdle.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<PersonDTO, PersonEntity, PersonRepository>{
    private final PersonRepository personRepository;
    private final PersonWithGroupRepository personWithGroupRepository;
    private final GroupRepository groupRepository;
    private final PersonWithWeaponRepository personWithWeaponRepository;
    private final WeaponRepository weaponRepository;
    private final SpeciesRepository speciesRepository;
    private final GenderRepository genderRepository;
    private final ArcRepository arcRepository;

    public PersonService(PersonRepository personRepository, PersonWithGroupRepository personWithGroupRepository, GroupRepository groupRepository, PersonWithWeaponRepository personWithWeaponRepository, WeaponRepository weaponRepository, SpeciesRepository speciesRepository, GenderRepository genderRepository, ArcRepository arcRepository) {
        super(personRepository);
        this.personRepository = personRepository;
        this.personWithGroupRepository = personWithGroupRepository;
        this.groupRepository = groupRepository;
        this.personWithWeaponRepository = personWithWeaponRepository;
        this.weaponRepository = weaponRepository;
        this.speciesRepository = speciesRepository;
        this.genderRepository = genderRepository;
        this.arcRepository = arcRepository;
    }

    @Override
    public PersonDTO findByName(String name) {
        return toDTO(personRepository.findByName(name));
    }

    @Override
    public PersonEntity toEntity(PersonDTO PersonDTO) {
        PersonEntity personEntity = new PersonEntity();

        personEntity.setName(PersonDTO.getName());
        personEntity.setSpecies(speciesRepository.findByName(PersonDTO.getSpecies()));
        personEntity.setGender(genderRepository.findByName(PersonDTO.getGender()));
        personEntity.setFirstArc(arcRepository.findByName(PersonDTO.getFirstArc()));

        if (personRepository.findByName(PersonDTO.getName()) != null) {
            personEntity.setGroups(personWithGroupRepository.findAllByPerson_Name(PersonDTO.getName()));
            personEntity.setWeapons(personWithWeaponRepository.findAllByPerson_Name(PersonDTO.getName()));
        }
        else {
            for (String weaponName : PersonDTO.getWeapons()) {
                WeaponEntity weaponEntity = weaponRepository.findByName(weaponName);
                personEntity.addWeapon(new PersonWithWeaponEntity(new PersonWithWeaponId(PersonDTO.getId(), weaponEntity.getId()), personEntity, weaponEntity));
            }
            for (String groupName : PersonDTO.getGroups()) {
                GroupEntity groupEntity = groupRepository.findByName(groupName);
                personEntity.addGroup(new PersonWithGroupEntity(new PersonWithGroupId(PersonDTO.getId(), groupEntity.getId()), personEntity, groupEntity));
            }
        }
        return personEntity;
    }

    @Override
    public PersonDTO toDTO(PersonEntity PersonEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(PersonEntity.getName());
        personDTO.setGender(PersonEntity.getGender().getName());
        personDTO.setFirstArc(PersonEntity.getFirstArc().getName());
        personDTO.setSpecies(PersonEntity.getSpecies().getName());
        for (PersonWithGroupEntity personWithGroupEntity : personWithGroupRepository.findAllByPerson_Id(PersonEntity.getId())) {
            personDTO.addGroup(groupRepository.findById(personWithGroupEntity.getGroup().getId()).get().getName());
        }
        for (PersonWithWeaponEntity personWithWeaponEntity : personWithWeaponRepository.findAllByPerson_Id(PersonEntity.getId())) {
            personDTO.addWeapon(groupRepository.findById(personWithWeaponEntity.getWeapon().getId()).get().getName());
        }
        return personDTO;
    }
}
