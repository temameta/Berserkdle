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
    public PersonEntity toEntity(PersonDTO DTO) {
        PersonEntity personEntity = new PersonEntity();

        personEntity.setName(DTO.getName());
        personEntity.setSpecies(speciesRepository.findByName(DTO.getSpecies()));
        personEntity.setGender(genderRepository.findByName(DTO.getGender()));
        personEntity.setFirstArc(arcRepository.findByName(DTO.getFirstArc()));

        if (personRepository.findByName(DTO.getName()) != null) {
            personEntity.setGroups(personWithGroupRepository.findAllByPerson_Name(DTO.getName()));
            personEntity.setWeapons(personWithWeaponRepository.findAllByPerson_Name(DTO.getName()));
        }
        else {
            for (String weaponName : DTO.getWeapons()) {
                WeaponEntity weaponEntity = weaponRepository.findByName(weaponName);
                personEntity.addWeapon(new PersonWithWeaponEntity(new PersonWithWeaponId(DTO.getId(), weaponEntity.getId()), personEntity, weaponEntity));
            }
            for (String groupName : DTO.getGroups()) {
                GroupEntity groupEntity = groupRepository.findByName(groupName);
                personEntity.addGroup(new PersonWithGroupEntity(new PersonWithGroupId(DTO.getId(), groupEntity.getId()), personEntity, groupEntity));
            }
        }
        return personEntity;
    }

    @Override
    public PersonDTO toDTO(PersonEntity entity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(entity.getName());
        personDTO.setGender(entity.getGender().getName());
        personDTO.setFirstArc(entity.getFirstArc().getName());
        personDTO.setSpecies(entity.getSpecies().getName());
        for (PersonWithGroupEntity personWithGroupEntity : personWithGroupRepository.findAllByPerson_Id(entity.getId())) {
            personDTO.addGroup(groupRepository.findById(personWithGroupEntity.getGroup().getId()).get().getName());
        }
        for (PersonWithWeaponEntity personWithWeaponEntity : personWithWeaponRepository.findAllByPerson_Id(entity.getId())) {
            personDTO.addWeapon(groupRepository.findById(personWithWeaponEntity.getWeapon().getId()).get().getName());
        }
        return personDTO;
    }
}
