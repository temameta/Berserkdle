package org.example.berserkdle.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.PersonEntity;
import org.example.berserkdle.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/person")
@AllArgsConstructor
@NoArgsConstructor
public class PersonController {
    PersonService personService;

    @GetMapping("/get/all")
    public List<PersonDTO> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/get/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/get/{name}")
    public PersonDTO getPersonByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @PostMapping("/create")
    public HttpStatus createPerson(@RequestBody PersonDTO personDTO) {
        personService.save(personDTO);
        return HttpStatus.OK;
    }
}
