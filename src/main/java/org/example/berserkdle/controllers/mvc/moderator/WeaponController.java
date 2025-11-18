package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.dtos.WeaponDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weapon")
public class WeaponController implements InterfaceController {
    @Override
    public String get() {
        return "";
    }

    @Override
    public String create(Model model) {
        WeaponDTO weaponDTO = new WeaponDTO();
        model.addAttribute("weapon", weaponDTO);
        return "weapon/creation";
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
