package org.example.berserkdle.services;

import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.entities.ArcEntity;
import org.example.berserkdle.repositories.ArcRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArcService extends AbstractService<ArcDTO, ArcEntity, ArcRepository> {

    public ArcService(ArcRepository repository) {
        super(repository);
    }

    @Override
    public ArcDTO toDTO(ArcEntity arcEntity) {
        return ArcDTO.builder()
                .name(arcEntity.getName())
                .build();
    }

    @Override
    public ArcEntity toEntity(ArcDTO arcDTO) {
        return ArcEntity.builder()
                .name(arcDTO.getName())
                .build();
    }

    @Cacheable(value = "arcNames", key = "'all'")
    public List<String> getAllNames() {
        return repository.getAllNames();
    }
}
