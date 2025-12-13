package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GenderDTO;
import org.example.berserkdle.entities.GenderEntity;
import org.example.berserkdle.repositories.GenderRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService extends AbstractService<GenderDTO, GenderEntity, GenderRepository>{

    public GenderService(GenderRepository repository) {
        super(repository);
    }

    @Override
    public GenderDTO toDTO(GenderEntity genderEntity) {
        return GenderDTO.builder()
                .name(genderEntity.getName())
                .build();
    }

    @Override
    public GenderEntity toEntity(GenderDTO genderDTO) {
        return GenderEntity.builder()
                .name(genderDTO.getName())
                .build();
    }

    @Cacheable(value = "genderNames", key = "'all'")
    public List<String> getAllNames() {
        return repository.getAllNames();
    }
}
