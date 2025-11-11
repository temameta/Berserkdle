package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity, ID> extends JpaRepository<E, ID> {
    E findByName(String name);
}
