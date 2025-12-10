package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.AbstractDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface InterfaceAPIController<D extends AbstractDTO> {
    @GetMapping("/search")
    List<String> search(@RequestParam String name);
}
