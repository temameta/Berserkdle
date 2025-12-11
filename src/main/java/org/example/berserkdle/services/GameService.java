package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GameView;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.dtos.RequestedPersonDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private final PersonService personService;
    private PersonDTO hiddenPerson;

    public GameService(PersonService personService) {
        this.personService = personService;
        initHiddenPerson();
    }

    public void initHiddenPerson() {
        try {
            String name = Files.readString(Paths.get("../resources/static/HiddenPerson.txt"), StandardCharsets.UTF_8);
            this.hiddenPerson = personService.findByName(name);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public RequestedPersonDto compare(RequestedPersonDto requestedPerson) {
        Map<String, Boolean> params = new HashMap<>();
        if (personService.findByName(requestedPerson.getName()) != null) {
            PersonDTO personFromBase = personService.findByName(requestedPerson.getName());
            params.put("firstArc", personFromBase.getFirstArc().equalsIgnoreCase(hiddenPerson.getFirstArc()));
            params.put("gender", personFromBase.getGender().equalsIgnoreCase(hiddenPerson.getGender()));
            params.put("species", personFromBase.getSpecies().equalsIgnoreCase(hiddenPerson.getSpecies()));
            params.put("groups", personFromBase.getGroups().equals(hiddenPerson.getGroups()));
            params.put("weapons", personFromBase.getWeapons().equals(hiddenPerson.getWeapons()));
        }
        return new RequestedPersonDto(requestedPerson.getName(), params);
    }
}
