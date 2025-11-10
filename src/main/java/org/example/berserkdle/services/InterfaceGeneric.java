package org.example.berserkdle.services;

import java.util.List;
import java.util.Optional;

public interface InterfaceGeneric<D, E> {
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