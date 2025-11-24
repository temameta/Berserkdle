package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/weapon")
public class WeaponController {
    private final WeaponService weaponService;

    @Autowired
    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @ModelAttribute("weaponModel")
    public WeaponDTO initWeapon() {
        return new WeaponDTO();
    }

    @GetMapping("/get")
    public String get() {
        return "";
    }

    @GetMapping("/create")
    public String create() {
        return "weapon/create";
    }

    @PostMapping("/create")
    public String create(@Valid WeaponDTO weaponModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            //log.warn("Ошибки валидации при добавлении компании: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("weaponModel", weaponModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weaponModel", bindingResult);
            return "redirect:/weapon/create";
        }
        weaponService.save(weaponModel);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weaponModel.getName() + " успешно добавлено!");
        return "redirect:/weapon/create";
    }

    @GetMapping("/update")
    public String update() {
        return "";
    }

    @GetMapping("/delete")
    public String delete() {
        return "";
    }
}
