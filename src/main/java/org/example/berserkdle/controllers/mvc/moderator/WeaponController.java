package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.services.WeaponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/weapon")
public class WeaponController implements InterfaceController<WeaponDTO> {
    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @Override
    public String get() {
        return "";
    }

    @Override
    public String create(Model model) {
        model.addAttribute("weapon", new WeaponDTO());
        return "weapon/creation";
    }

    @Override
    public String create(WeaponDTO weaponDTO, RedirectAttributes redirectAttributes) {
        weaponService.save(weaponDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weaponDTO.getName() + " успешно добавлено!");
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
