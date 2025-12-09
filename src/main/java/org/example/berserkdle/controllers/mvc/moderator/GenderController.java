package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.GenderDTO;
import org.example.berserkdle.services.GenderService;
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
@RequestMapping("/moderator/gender")
public class GenderController {
    private final GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        log.info("Инициализация контроллера GenderController");
        this.genderService = genderService;
    }

    @ModelAttribute("genderModel")
    public GenderDTO initGender() {
        return new GenderDTO();
    }

    @GetMapping("/all")
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) String search,
            Model model
    ) {
        log.info("Открыта страница всех полов");
        if (search != null && !search.trim().isEmpty()) {
            log.info("Поиск пола");
            model.addAttribute("genders", genderService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех полов");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<GenderDTO> genderPage = genderService.allPaginated(pageable);

            model.addAttribute("genders", genderPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", genderPage.getTotalPages());
            model.addAttribute("totalItems", genderPage.getTotalElements());
        }
        return "gender/get-all";
    }

    @GetMapping("/{name}")
    public String getGender(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница пола {}", name);
        model.addAttribute("gender", genderService.findByName(name));
        return "gender/gender-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания новой пола");
        return "gender/create";
    }

    @PostMapping("/create")
    public String create(@Valid GenderDTO genderModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание новой пола {}", genderModel.getName());
        if (bindingResult.hasErrors()) {
            log.error("Название пола {} некорректно", genderModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("genderModel", genderModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.genderModel", bindingResult);
            return "redirect:/moderator/gender/create";
        }
        log.info("Создание новой пола {}...", genderModel.getName());
        genderService.save(genderModel);
        log.info("Пол успешно {} добавлен!", genderModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + genderModel.getName() + " успешно добавлен!");
        return "redirect:/moderator/gender/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления пола {}", name);
        model.addAttribute("oldName", name);
        return "gender/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid GenderDTO genderModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление пола {}", name);
        if (bindingResult.hasErrors()) {
            log.error("Название пола {} некорректно", genderModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("genderModel", genderModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.genderModel", bindingResult);
            return "redirect:/moderator/gender/{name}/update";
        }
        log.info("Обновление пола {}...", name);
        genderService.update(name, genderModel.getName());
        log.info("Пол {} успешно добавлен! Новое название: {}", name, genderModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + genderModel.getName() + " успешно добавлен!");
        return "redirect:/moderator/gender/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления пола {}", name);
        model.addAttribute("gender", genderService.findByName(name));
        return "gender/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(GenderDTO gender, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление пола {}", name);
        genderService.delete(gender);
        log.info("Пол {} успешно добавлен!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + gender.getName() + " успешно добавлен!");
        return "redirect:/moderator/gender/all";
    }
}
