package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.AbstractDTO;
import org.example.berserkdle.entities.AbstractEntity;
import org.example.berserkdle.services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;


public abstract class AbstractAPIController<D extends AbstractDTO, E extends AbstractEntity, S extends InterfaceService<D, E>> implements InterfaceAPIController<D> {
    private final S service;

    @Autowired
    protected AbstractAPIController(S service) {
        this.service = service;
    }

    @Override
    public List<D> getAll() {
        return service.findAll();
    }

    @Override
    public HttpStatus create(D DTO) {
        service.save(DTO);
        return HttpStatus.OK;
    }

    @Override
    public D getByName(String name) {
        return service.findByName(name);
    }

    @Override
    public D getById(Long id) {
        return service.findById(id);
    }
}
