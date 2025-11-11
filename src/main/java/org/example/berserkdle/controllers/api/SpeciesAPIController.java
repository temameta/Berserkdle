package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.SpeciesDTO;
import org.example.berserkdle.entities.SpeciesEntity;
import org.example.berserkdle.services.SpeciesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/species")
public class SpeciesAPIController extends AbstractAPIController<SpeciesDTO, SpeciesEntity, SpeciesService> {

    public SpeciesAPIController(SpeciesService service) {
        super(service);
    }
}
