package org.example.berserkdle.services;

import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.entities.ArcEntity;
import org.example.berserkdle.repositories.ArcRepository;
import org.springframework.stereotype.Service;

@Service
public class ArcService extends AbstractService<ArcDTO, ArcEntity, ArcRepository>{

    public ArcService(ArcRepository repository) {
        super(repository);
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
