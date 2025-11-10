package org.example.berserkdle.services;

import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.entities.ArcEntity;
import org.example.berserkdle.repositories.ArcRepository;
import org.springframework.stereotype.Service;

@Service
public class ArcService extends AbstractService<ArcDTO, ArcEntity, ArcRepository>{
    private final ArcRepository arcRepository;

    public ArcService(ArcRepository arcRepository) {
        super(arcRepository);
        this.arcRepository = arcRepository;
    }

    @Override
    public ArcDTO findByName(String name) {
        return toDTO(arcRepository.findByName(name));
    }

    @Override
    public ArcDTO toDTO(ArcEntity arcEntity) {
        ArcDTO arcDTO = new ArcDTO();
        arcDTO.setId(arcEntity.getId());
        arcDTO.setName(arcEntity.getName());
        return arcDTO;
    }

    @Override
    public ArcEntity toEntity(ArcDTO arcDTO) {
        ArcEntity arcEntity = new ArcEntity();
        arcEntity.setId(arcDTO.getId());
        arcEntity.setName(arcDTO.getName());
        return arcEntity;
    }
}
