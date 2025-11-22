package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.dtos.AbstractDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface InterfaceController<D extends AbstractDTO> {
    @GetMapping("/get")
    String get();
    @GetMapping("/create")
    String create(Model model);
    @PostMapping("/create")
    String create(D DTO, BindingResult bindingResult, RedirectAttributes redirectAttributes);
    @GetMapping("/update")
    String update();
    @GetMapping("/delete")
    String delete();
}
