package org.example.berserkdle.controllers.mvc.moderator;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface InterfaceController {
    @GetMapping("/get")
    String get();
    @GetMapping("/create")
    String create(Model model);
    @GetMapping("/update")
    String update();
    @GetMapping("/delete")
    String delete();
}
