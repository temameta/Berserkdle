package org.example.berserkdle;

import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.Person;
import org.example.berserkdle.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BerserkdleApplicationTests {
    private final PersonService personService;

    @Autowired
    BerserkdleApplicationTests(PersonService personService) {
        this.personService = personService;
    }

    @Test
    void checkDuplicate() {
        personService.createNew(new PersonDTO(
                "Гатс",
                "Мужской",
                "Золотой век",
                "Человек",
                List.of("Банда сокола"),
                List.of("Двуручное оружие", "Арбалет")));

        personService.createNew(new PersonDTO(
                "Каска",
                "Женский",
                "Золотой век",
                "Человек",
                List.of("Банда сокола"),
                List.of("Одноручный меч")));

        System.out.println("Вывод групп Каски");
        personService.findByName("Каска").getGroups().forEach(System.out::println);
        System.out.println("Вывод Гатса");
        System.out.println(personService.findByName("Гатс"));
        System.out.println("Вывод групп Гатса");
        personService.findByName("Гатс").getGroups().forEach(System.out::println);

    }

    @Test
    void contextLoads() {
    }

}
