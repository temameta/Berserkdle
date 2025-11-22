package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.services.WeaponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/weapon")
public class WeaponController implements InterfaceController<WeaponDTO> {
    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @ModelAttribute("weaponModel")
    public WeaponDTO initWeapon() {
        return new WeaponDTO();
    }

    @Override
    public String get() {
        return "";
    }

    @Override
    public String create() {
        return "weapon/create";
    }

    @Override
    public String create(WeaponDTO weaponModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            //log.warn("Ошибки валидации при добавлении компании: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("weapon", weaponModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weapon", bindingResult);
            return "redirect:/weapon/create";
        }
        weaponService.save(weaponModel);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weaponModel.getName() + " успешно добавлено!");
        return "redirect:/weapon/create";
    }

    @Override
    public String update() {
        return "";
    }

    @Override
    public String delete() {
        return "";
    }
}
