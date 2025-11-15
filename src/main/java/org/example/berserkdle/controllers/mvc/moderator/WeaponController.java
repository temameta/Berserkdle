package org.example.berserkdle.controllers.mvc.moderator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moderator/weapon")
public class WeaponController implements InterfaceController {
    @Override
    public String get() {
        return "";
    }

    @Override
    public String create() {
        return "";
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
