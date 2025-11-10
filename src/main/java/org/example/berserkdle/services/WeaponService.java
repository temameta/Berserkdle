package org.example.berserkdle.services;

import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.entities.WeaponEntity;
import org.example.berserkdle.repositories.WeaponRepository;
import org.springframework.stereotype.Service;

@Service
public class WeaponService extends AbstractService<WeaponDTO, WeaponEntity, WeaponRepository> {
    private final WeaponRepository weaponRepository;

    public WeaponService(WeaponRepository weaponRepository) {
        super(weaponRepository);
        this.weaponRepository = weaponRepository;
    }

    @Override
    public WeaponDTO findByName(String name) {
        return toDTO(weaponRepository.findByName(name));
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