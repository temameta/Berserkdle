package org.example.berserkdle.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.entities.WeaponEntity;
import org.example.berserkdle.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public List<WeaponDTO> findAll() {
        return toWeaponDTO(weaponRepository.findAll());
    }

    public WeaponDTO findById(Long id) {
        return toWeaponDTO(weaponRepository.findById(id));
    }

    public WeaponDTO findByName(String name) {
        return toWeaponDTO(weaponRepository.findByName(name));
    }

    public void save(WeaponDTO weaponDTO) {
        weaponRepository.save(toWeaponEntity(weaponDTO));
    }

    public void save(List<WeaponDTO> weaponDTOs) {
        for (WeaponDTO weaponDTO : weaponDTOs) {
            save(weaponDTO);
        }
    }

    public WeaponEntity toWeaponEntity(WeaponDTO weaponDTO) {
        WeaponEntity weaponEntity = new WeaponEntity();
        weaponEntity.setId(weaponDTO.getId());
        weaponEntity.setName(weaponDTO.getName());
        return weaponEntity;
    }

    public List<WeaponEntity> toWeaponEntity(List<WeaponDTO> weaponDTOs) {
        List<WeaponEntity> weaponEntities = new ArrayList<>();
        for (WeaponDTO weaponDTO : weaponDTOs) {
            weaponEntities.add(toWeaponEntity(weaponDTO));
        }
        return weaponEntities;
    }

    public WeaponDTO toWeaponDTO(Optional<WeaponEntity> optionalWeaponEntity) {
        if (optionalWeaponEntity.isPresent())
            return toWeaponDTO(optionalWeaponEntity.get());
        return null;
    }

    public WeaponDTO toWeaponDTO(WeaponEntity weaponEntity) {
        WeaponDTO weaponDTO = new WeaponDTO();
        weaponDTO.setId(weaponEntity.getId());
        weaponDTO.setName(weaponEntity.getName());
        return weaponDTO;
    }

    public List<WeaponDTO> toWeaponDTO(List<WeaponEntity> weaponEntities) {
        List<WeaponDTO> weaponDTOs = new ArrayList<>();
        for (WeaponEntity weaponEntity : weaponEntities) {
            weaponDTOs.add(toWeaponDTO(weaponEntity));
        }
        return weaponDTOs;
    }
}
