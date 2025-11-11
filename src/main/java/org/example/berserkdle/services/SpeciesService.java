package org.example.berserkdle.services;

import org.example.berserkdle.dtos.SpeciesDTO;
import org.example.berserkdle.entities.SpeciesEntity;
import org.example.berserkdle.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService extends AbstractService<SpeciesDTO, SpeciesEntity, SpeciesRepository> {

    public SpeciesService(SpeciesRepository repository) {
        super(repository);
    }

    @Override
    public SpeciesDTO toDTO(SpeciesEntity speciesEntity) {
        return SpeciesDTO.builder()
                .id(speciesEntity.getId())
                .name(speciesEntity.getName())
                .build();
    }

    @Override
    public SpeciesEntity toEntity(SpeciesDTO speciesDTO) {
        return SpeciesEntity.builder()
                .id(speciesDTO.getId())
                .name(speciesDTO.getName())
                .build();
    }
}
