package org.example.berserkdle.services;

import lombok.RequiredArgsConstructor;
import org.example.berserkdle.entities.AbstractEntity;
import org.example.berserkdle.repositories.AbstractRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractService<D, E extends AbstractEntity, R extends AbstractRepository<E>> implements InterfaceService<D, E> {
    private final R repository;

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
        for (D DTO : DTOs)
            save(DTO);
    }

    @Transactional(readOnly = true)
    @Override
    public D findByName(String name) {
        return null;
    };

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
}
