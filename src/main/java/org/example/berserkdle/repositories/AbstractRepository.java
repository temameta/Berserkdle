package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
    E findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
    List<E> findByNameContainingIgnoreCase(String name);
}
