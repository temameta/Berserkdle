package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import org.example.berserkdle.dtos.WeaponDTO;
import org.example.berserkdle.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/all")
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) String search,
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("weapon", weaponService.findByName(search));
            model.addAttribute("search", search);
        } else {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<WeaponDTO> weaponPage = weaponService.allPaginated(pageable);

            model.addAttribute("weapon", weaponPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", weaponPage.getTotalPages());
            model.addAttribute("totalItems", weaponPage.getTotalElements());
        }
        return "weapon/get-all";
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
