package org.example.berserkdle.services;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.*;
import org.example.berserkdle.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final GroupRepository groupRepository;
    private final WeaponRepository weaponRepository;
    private final ModelMapper mapper;

    @Autowired
    public PersonService(PersonRepository repository, GroupRepository groupRepository, WeaponRepository weaponRepository, ModelMapper mapper) {
        this.personRepository = repository;
        this.groupRepository = groupRepository;
        this.weaponRepository = weaponRepository;
        this.mapper = mapper;
    }

    @Transactional
    @CacheEvict(cacheNames = {"persons", "personNames"}, allEntries = true)
    public void delete(String name) {
        personRepository.deleteByName(name);
    }

    public void createNew(PersonDTO dto) {
        List<Weapon> weapons = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        for (String name : dto.getWeapons()) {
            weapons.add(new Weapon(name));
        }
        for (String name : dto.getGroups()) {
            Group group = groupRepository.findByName(name);
            System.out.println(group);
            if (group == null) {
                group = new Group(name);
                groupRepository.save(group);
            }
            groups.add(group);
        }
        Person person = new Person(
                dto.getName(),
                dto.getGender(),
                dto.getFirstArc(),
                dto.getSpecies(),
                groups,
                weapons
        );
        personRepository.save(person);
    }

    public PersonDTO findByName(String name) {
        Person person = personRepository.findByName(name);
        return PersonDTO.builder()
                .name(person.getName())
                .gender(person.getGender())
                .species(person.getSpecies())
                .firstArc(person.getArc())
                .groups(person.getGroups().stream().map(Group::getName).collect(Collectors.toList()))
                .weapons(person.getWeapons().stream().map(Weapon::getName).collect(Collectors.toList()))
                .build();
    }

    public boolean existsByName(String name) {
        return personRepository.existsByName(name);
    }

    public Page<PersonDTO> allPaginated(Pageable pageable) {
        log.debug("Получение компаний с пагинацией: страница {}, размер {}",
                pageable.getPageNumber(), pageable.getPageSize());
        return personRepository.findAll(pageable)
                .map(person -> mapper.map(person, PersonDTO.class));
    }

    @Cacheable(value = "personNames", key = "'all'")
    public List<String> getAllNames() {
        return personRepository.getAllNames();
    }
}
