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
    public WeaponDTO toDTO(WeaponEntity weaponEntity) {
        return WeaponDTO.builder()
                .name(weaponEntity.getName())
                .build();
    }

    @Override
    public WeaponEntity toEntity(WeaponDTO weaponDTO) {
        return WeaponEntity.builder()
                .name(weaponDTO.getName())
                .build();
    }
}