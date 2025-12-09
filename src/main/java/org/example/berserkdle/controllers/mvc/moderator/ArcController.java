package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.services.ArcService;
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
@RequestMapping("/moderator/arc")
public class ArcController {
    private final ArcService arcService;

    @Autowired
    public ArcController(ArcService arcService) {
        log.info("Инициализация контроллера ArcController");
        this.arcService = arcService;
    }

    @ModelAttribute("arcModel")
    public ArcDTO initArc() {
        return new ArcDTO();
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
            model.addAttribute("arcs", arcService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех оружий");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<ArcDTO> arcPage = arcService.allPaginated(pageable);

            model.addAttribute("arcs", arcPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", arcPage.getTotalPages());
            model.addAttribute("totalItems", arcPage.getTotalElements());
        }
        return "arc/get-all";
    }

    @GetMapping("/{name}")
    public String getArc(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница оружия {}", name);
        model.addAttribute("arc", arcService.findByName(name));
        return "arc/arc-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания нового оружия");
        return "arc/create";
    }

    @PostMapping("/create")
    public String create(@Valid ArcDTO arcModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание нового оружия {}", arcModel.getName());
        if (bindingResult.hasErrors()) {
            log.error("Название оружия {} некорректно", arcModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("arcModel", arcModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.arcModel", bindingResult);
            return "redirect:/moderator/arc/create";
        }
        log.info("Создание нового оружия {}...", arcModel.getName());
        arcService.save(arcModel);
        log.info("Оружие успешно {} создано!", arcModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + arcModel.getName() + " успешно добавлено!");
        return "redirect:/moderator/arc/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления оружия {}", name);
        model.addAttribute("oldName", name);
        return "arc/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid ArcDTO arcModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление оружия {}", name);
        if (bindingResult.hasErrors()) {
            log.error("Название оружия {} некорректно", arcModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("arcModel", arcModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.arcModel", bindingResult);
            return "redirect:/moderator/arc/{name}/update";
        }
        log.info("Обновление оружия {}...", name);
        arcService.update(name, arcModel.getName());
        log.info("Оружие {} успешно обновлено! Новое название: {}", name, arcModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + arcModel.getName() + " успешно обновлено!");
        return "redirect:/moderator/arc/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления оружия {}", name);
        model.addAttribute("arc", arcService.findByName(name));
        return "arc/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(ArcDTO arc, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление оружия {}", name);
        arcService.delete(arc);
        log.info("Оружие {} успешно удалено!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Оружие " + arc.getName() + " успешно удалено!");
        return "redirect:/moderator/arc/all";
    }
}
