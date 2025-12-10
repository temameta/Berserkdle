package org.example.berserkdle.services;

import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.*;
import org.example.berserkdle.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<PersonDTO, PersonEntity, PersonRepository>{
    private final PersonWithGroupRepository personWithGroupRepository;
    private final GroupRepository groupRepository;
    private final PersonWithWeaponRepository personWithWeaponRepository;
    private final WeaponRepository weaponRepository;
    private final SpeciesRepository speciesRepository;
    private final GenderRepository genderRepository;
    private final ArcRepository arcRepository;

    public PersonService(PersonRepository repository, PersonWithGroupRepository personWithGroupRepository, GroupRepository groupRepository, PersonWithWeaponRepository personWithWeaponRepository, WeaponRepository weaponRepository, SpeciesRepository speciesRepository, GenderRepository genderRepository, ArcRepository arcRepository) {
        super(repository);
        this.personWithGroupRepository = personWithGroupRepository;
        this.groupRepository = groupRepository;
        this.personWithWeaponRepository = personWithWeaponRepository;
        this.weaponRepository = weaponRepository;
        this.speciesRepository = speciesRepository;
        this.genderRepository = genderRepository;
        this.arcRepository = arcRepository;
    }

    @Override
    public PersonEntity toEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();

        personEntity.setName(personDTO.getName());
        personEntity.setSpecies(speciesRepository.findByName(personDTO.getSpecies()));
        personEntity.setGender(genderRepository.findByName(personDTO.getGender()));
        personEntity.setFirstArc(arcRepository.findByName(personDTO.getFirstArc()));
        
        return personEntity;
    }
    @Override
    public void save(PersonDTO DTO) {
        // Сначала сохраняем персону
        PersonEntity personEntity = toEntity(DTO);
        repository.save(personEntity); // Теперь у personEntity будет ID

        // Затем создаем и сохраняем связи
        createPersonAssociations(DTO, personEntity);
    }

    private void createPersonAssociations(PersonDTO personDTO, PersonEntity personEntity) {
        // Создаем связи с оружиями
        for (String weaponName : personDTO.getWeapons()) {
            WeaponEntity weaponEntity = weaponRepository.findByName(weaponName);
            if (weaponEntity != null) {
                PersonWithWeaponEntity personWithWeapon = new PersonWithWeaponEntity(
                        new PersonWithWeaponId(personEntity.getId(), weaponEntity.getId()),
                        personEntity,
                        weaponEntity
                );
                personWithWeaponRepository.save(personWithWeapon);
            }
        }

        // Создаем связи с группами
        for (String groupName : personDTO.getGroups()) {
            GroupEntity groupEntity = groupRepository.findByName(groupName);
            if (groupEntity != null) {
                PersonWithGroupEntity personWithGroup = new PersonWithGroupEntity(
                        new PersonWithGroupId(personEntity.getId(), groupEntity.getId()),
                        personEntity,
                        groupEntity
                );
                personWithGroupRepository.save(personWithGroup);
            }
        }
    }

    @Override
    public PersonDTO toDTO(PersonEntity personEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(personEntity.getName());
        personDTO.setGender(personEntity.getGender().getName());
        personDTO.setFirstArc(personEntity.getFirstArc().getName());
        personDTO.setSpecies(personEntity.getSpecies().getName());
        for (PersonWithGroupEntity personWithGroupEntity : personWithGroupRepository.findAllByPerson_Id(personEntity.getId())) {
            personDTO.addGroup(groupRepository.findById(personWithGroupEntity.getGroup().getId()).get().getName());
        }
        for (PersonWithWeaponEntity personWithWeaponEntity : personWithWeaponRepository.findAllByPerson_Id(personEntity.getId())) {
            personDTO.addWeapon(weaponRepository.findById(personWithWeaponEntity.getWeapon().getId()).get().getName());
        }
        return personDTO;
    }
}
