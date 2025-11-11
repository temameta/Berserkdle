package org.example.berserkdle.services;

import org.example.berserkdle.dtos.AbstractDTO;
import org.example.berserkdle.entities.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface InterfaceService<D extends AbstractDTO, E extends AbstractEntity> {
    List<D> findAll();
    D findById(Long id);
    D findByName(String name);
    D toDTO(E entity);
    List<D> toDTO(List<E> entities);
    D toDTO(Optional<E> optionalEntity);
    E toEntity(D DTO);
    List<E> toEntity(List<D> DTOs);
    void save(D DTO);
    void save(List<D> DTOs);
}