package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GenderDTO;
import org.example.berserkdle.entities.GenderEntity;
import org.example.berserkdle.repositories.GenderRepository;
import org.springframework.stereotype.Service;

@Service
public class GenderService extends AbstractService<GenderDTO, GenderEntity, GenderRepository>{
    GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        super(genderRepository);
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderDTO findByName(String name) {
        return toDTO(genderRepository.findByName(name));
    }

    @Override
    public GenderDTO toDTO(GenderEntity genderEntity) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setName(genderEntity.getName());
        genderDTO.setId(genderEntity.getId());
        return genderDTO;
    }

    @Override
    public GenderEntity toEntity(GenderDTO genderDTO) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setName(genderDTO.getName());
        genderEntity.setId(genderDTO.getId());
        return genderEntity;
    }
}
