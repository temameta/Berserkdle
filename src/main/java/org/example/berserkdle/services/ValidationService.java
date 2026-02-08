package org.example.berserkdle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final PersonService personService;

    @Autowired
    public ValidationService(PersonService personService) {
        this.personService = personService;
    }

    public boolean isExist(String className, String name) {
        return switch(className) {
            case "PersonDTO" -> personService.existsByName(name);
            default -> false;
        };
    }
}
