package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.GenderDTO;
import org.example.berserkdle.entities.GenderEntity;
import org.example.berserkdle.services.GenderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gender")
public class GenderAPIController extends AbstractAPIController<GenderDTO, GenderEntity, GenderService> {

    public GenderAPIController(GenderService service) {
        super(service);
    }
}
