package org.example.berserkdle.services;

import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.entities.WeaponEntity;
import org.example.berserkdle.repositories.WeaponRepository;
import org.springframework.stereotype.Service;

@Service
public class WeaponService extends AbstractService<WeaponDTO, WeaponEntity, WeaponRepository> {

    public WeaponService(WeaponRepository repository) {
        super(repository);
    }

    @Override
    public WeaponDTO toDTO(WeaponEntity entity) {
        WeaponDTO weaponDTO = new WeaponDTO();
        weaponDTO.setId(entity.getId());
        weaponDTO.setName(entity.getName());
        return weaponDTO;
    }

    @Override
    public WeaponEntity toEntity(WeaponDTO DTO) {
        WeaponEntity weaponEntity = new WeaponEntity();
        weaponEntity.setId(DTO.getId());
        weaponEntity.setName(DTO.getName());
        return weaponEntity;
    }
}