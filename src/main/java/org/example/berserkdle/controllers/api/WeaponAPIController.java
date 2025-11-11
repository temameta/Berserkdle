package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.entities.WeaponEntity;
import org.example.berserkdle.services.WeaponService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weapon")
public class WeaponAPIController extends AbstractAPIController<WeaponDTO, WeaponEntity, WeaponService> {

    public WeaponAPIController(WeaponService service) {
        super(service);
    }
}

