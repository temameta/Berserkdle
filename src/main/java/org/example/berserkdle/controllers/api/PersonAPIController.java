package org.example.berserkdle.controllers.api;

import lombok.RequiredArgsConstructor;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.entities.PersonEntity;
import org.example.berserkdle.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/person")
public class PersonAPIController extends AbstractAPIController<PersonDTO, PersonEntity, PersonService> {

    public PersonAPIController(PersonService service) {
        super(service);
    }
}
