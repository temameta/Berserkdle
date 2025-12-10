package org.example.berserkdle.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.AbstractDTO;
import org.example.berserkdle.entities.AbstractEntity;
import org.example.berserkdle.services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
public abstract class AbstractAPIController<D extends AbstractDTO, E extends AbstractEntity, S extends InterfaceService<D, E>> implements InterfaceAPIController<D> {
    private final S service;

    @Autowired
    protected AbstractAPIController(S service) {
        this.service = service;
    }

    @Override
    public List<String> search(@RequestParam String query) {
        log.info("Лог из апи контроллера: поиск сущности");
        return service.search(query)
                .stream()
                .map(E::getName)
                .toList();
    }
}
