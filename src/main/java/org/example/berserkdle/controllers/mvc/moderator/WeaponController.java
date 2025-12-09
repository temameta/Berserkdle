package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping("/moderator/weapon")
public class WeaponController {
    private final WeaponService weaponService;

    @Autowired
    public WeaponController(WeaponService weaponService) {
        log.info("Инициализация контроллера WeaponController");
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
        log.info("Открыта страница всех оружий");
        if (search != null && !search.trim().isEmpty()) {
            log.info("Поиск оружия");
            model.addAttribute("weapons", weaponService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех оружий");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<WeaponDTO> weaponPage = weaponService.allPaginated(pageable);

            model.addAttribute("weapons", weaponPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", weaponPage.getTotalPages());
            model.addAttribute("totalItems", weaponPage.getTotalElements());
        }
        return "weapon/get-all";
    }

    @GetMapping("/{name}")
    public String getWeapon(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница оружия {}", name);
        model.addAttribute("weapon", weaponService.findByName(name));
        return "weapon/weapon-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания нового оружия");
        return "weapon/create";
    }

    @PostMapping("/create")
    public String create(@Valid WeaponDTO weaponModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание нового оружия");
        if (bindingResult.hasErrors()) {
            log.error("Название оружия {} некорректно", weaponModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("weaponModel", weaponModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weaponModel", bindingResult);
            return "redirect:/moderator/weapon/create";
        }
        log.info("Создание нового оружия...");
        weaponService.save(weaponModel);
        log.info("Оружие успешно создано!");
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weaponModel.getName() + " успешно добавлено!");
        return "redirect:/moderator/weapon/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления оружия {}", name);
        model.addAttribute("oldName", name);
        return "weapon/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid WeaponDTO weaponModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление оружия {}", weaponModel.getName());
        if (bindingResult.hasErrors()) {
            log.error("Название оружия {} некорректно", weaponModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("weaponModel", weaponModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weaponModel", bindingResult);
            return "redirect:/moderator/weapon/{name}/update";
        }
        log.info("Обновление оружия {}...", weaponModel.getName());
        weaponService.update(name, weaponModel.getName());
        log.info("Оружие {} успешно обновлено! Новое название: {}", weaponModel.getName(), name);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weaponModel.getName() + " успешно обновлено!");
        return "redirect:/moderator/weapon/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления оружия {}", name);
        model.addAttribute("weapon", weaponService.findByName(name));
        return "weapon/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(WeaponDTO weapon, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление оружия {}", name);
        weaponService.delete(weapon);
        log.info("Оружие {} успешно удалено!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + weapon.getName() + " успешно удалено!");
        return "redirect:/moderator/weapon/all";
    }
}
