package org.example.berserkdle.services;

import lombok.RequiredArgsConstructor;
import org.example.berserkdle.dtos.AbstractDTO;
import org.example.berserkdle.entities.AbstractEntity;
import org.example.berserkdle.repositories.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<D extends AbstractDTO, E extends AbstractEntity, R extends AbstractRepository<E>> implements InterfaceService<D, E> {
    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<D> findAll() {
        return toDTO(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public D findById(Long id) {
        return toDTO(repository.findById(id));
    }

    @Transactional
    @Override
    public void save(D DTO) {
        repository.save(toEntity(DTO));
    }

    @Transactional
    @Override
    public void save(List<D> DTOs) {
        repository.saveAll(toEntity(DTOs));
    }

    @Transactional(readOnly = true)
    @Override
    public D findByName(String name) {
        return toDTO(repository.findByName(name));
    }

    @Override
    public List<D> toDTO(List<E> entities) {
        List<D> DTOs = new ArrayList<>();
        for (E entity : entities)
            DTOs.add(toDTO(entity));
        return DTOs;
    }

    @Override
    public D toDTO(Optional<E> optionalEntity) {
        if (optionalEntity.isPresent())
            return toDTO(optionalEntity.get());
        return null;
    }

    @Override
    public List<E> toEntity(List<D> DTOs) {
        List<E> entities = new ArrayList<>();
        for (D DTO : DTOs)
            entities.add(toEntity(DTO));
        return entities;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
