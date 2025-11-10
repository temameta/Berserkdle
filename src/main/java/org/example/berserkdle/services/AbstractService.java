package org.example.berserkdle.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
public abstract class AbstractService<D, E, R extends JpaRepository<E, Long>> implements InterfaceGeneric<D, E> {
    private final R repository;

    @Override
    public List<D> findAll() {
        return toDTO(repository.findAll());
    }

    @Override
    public D findById(Long id) {
        return toDTO(repository.findById(id));
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

    @Override
    public void save(D DTO) {
        repository.save(toEntity(DTO));
    }

    @Override
    public void save(List<D> DTOs) {
        for (D DTO : DTOs)
            save(DTO);
    }
}
