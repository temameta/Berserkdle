package org.example.berserkdle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final ArcService arcService;
    private final GenderService genderService;
    private final GroupService groupService;
    private final PersonService personService;
    private final SpeciesService speciesService;
    private final WeaponService weaponService;

    @Autowired
    public ValidationService(ArcService arcService, GenderService genderService, GroupService groupService, PersonService personService, SpeciesService speciesService, WeaponService weaponService) {
        this.arcService = arcService;
        this.genderService = genderService;
        this.groupService = groupService;
        this.personService = personService;
        this.speciesService = speciesService;
        this.weaponService = weaponService;
    }

    public boolean isExist(String className, String name) {
        System.out.println(className);
        return switch(className) {
            case "WeaponDTO" -> weaponService.existsByName(name);
            case "ArcDTO" -> arcService.existsByName(name);
            case "GenderDTO" -> genderService.existsByName(name);
            case "GroupDTO" -> groupService.existsByName(name);
            case "PersonDTO" -> personService.existsByName(name);
            case "SpeciesDTO" -> speciesService.existsByName(name);
            default -> false;
        };
    }
}
