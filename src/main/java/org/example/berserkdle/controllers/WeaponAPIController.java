package org.example.berserkdle.controllers;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weapon")
@Data
@RequiredArgsConstructor
public class WeaponAPIController {
    private final WeaponService weaponService;

    @GetMapping("/get/all")
    public List<WeaponDTO> findAll() {
        return weaponService.findAll();
    }

    @PostMapping("/create")
    public HttpStatus createWeapon(@RequestBody WeaponDTO weaponDTO) {
        weaponService.save(weaponDTO);
        return HttpStatus.OK;
    }
}
