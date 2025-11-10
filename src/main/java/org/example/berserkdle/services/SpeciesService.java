package org.example.berserkdle.services;

import org.example.berserkdle.dtos.SpeciesDTO;
import org.example.berserkdle.entities.SpeciesEntity;
import org.example.berserkdle.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService extends AbstractService<SpeciesDTO, SpeciesEntity, SpeciesRepository> {
    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        super(speciesRepository);
        this.speciesRepository = speciesRepository;
    }

    @Override
    public SpeciesDTO findByName(String name) {
        return toDTO(speciesRepository.findByName(name));
    }

    @Override
    public SpeciesDTO toDTO(SpeciesEntity speciesEntity) {
        SpeciesDTO speciesDTO = new SpeciesDTO();
        speciesDTO.setName(speciesEntity.getName());
        speciesDTO.setId(speciesEntity.getId());
        return speciesDTO;
    }

    @Override
    public SpeciesEntity toEntity(SpeciesDTO speciesDTO) {
        SpeciesEntity speciesEntity = new SpeciesEntity();
        speciesEntity.setName(speciesDTO.getName());
        speciesEntity.setId(speciesDTO.getId());
        return speciesEntity;
    }
}
