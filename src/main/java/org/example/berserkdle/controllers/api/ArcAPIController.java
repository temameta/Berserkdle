package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.entities.ArcEntity;
import org.example.berserkdle.services.ArcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/arc")
public class ArcAPIController extends AbstractAPIController<ArcDTO, ArcEntity, ArcService> {

    public ArcAPIController(ArcService service) {
        super(service);
    }
}
