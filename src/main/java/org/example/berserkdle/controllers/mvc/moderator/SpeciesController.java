package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.SpeciesDTO;
import org.example.berserkdle.services.SpeciesService;
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
@RequestMapping("/moderator/species")
public class SpeciesController {
    private final SpeciesService speciesService;

    @Autowired
    public SpeciesController(SpeciesService speciesService) {
        log.info("Инициализация контроллера SpeciesController");
        this.speciesService = speciesService;
    }

    @ModelAttribute("speciesModel")
    public SpeciesDTO initSpecies() {
        return new SpeciesDTO();
    }

    @GetMapping("/all")
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) String search,
            Model model
    ) {
        log.info("Открыта страница всех рас");
        if (search != null && !search.trim().isEmpty()) {
            log.info("Поиск расы");
            model.addAttribute("search", speciesService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех рас");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<SpeciesDTO> speciesPage = speciesService.allPaginated(pageable);

            model.addAttribute("specieses", speciesPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", speciesPage.getTotalPages());
            model.addAttribute("totalItems", speciesPage.getTotalElements());
        }
        return "species/get-all";
    }

    @GetMapping("/{name}")
    public String getSpecies(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница расы {}", name);
        model.addAttribute("species", speciesService.findByName(name));
        return "species/species-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания новой расы");
        return "species/create";
    }

    @PostMapping("/create")
    public String create(@Valid SpeciesDTO speciesModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание новой расы {}", speciesModel.getName());
        if (bindingResult.hasErrors()) {
            log.error("Название расы {} некорректно", speciesModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("speciesModel", speciesModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.speciesModel", bindingResult);
            return "redirect:/moderator/species/create";
        }
        log.info("Создание новой расы {}...", speciesModel.getName());
        speciesService.save(speciesModel);
        log.info("Раса успешно {} создана!", speciesModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Раса " + speciesModel.getName() + " успешно добавлена!");
        return "redirect:/moderator/species/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления расы {}", name);
        model.addAttribute("oldName", name);
        return "species/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid SpeciesDTO speciesModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление расы {}", name);
        if (bindingResult.hasErrors()) {
            log.error("Название расы {} некорректно", speciesModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("speciesModel", speciesModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.speciesModel", bindingResult);
            return "redirect:/moderator/species/{name}/update";
        }
        log.info("Обновление расы {}...", name);
        speciesService.update(name, speciesModel.getName());
        log.info("Раса {} успешно обновлена! Новое название: {}", name, speciesModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Раса " + speciesModel.getName() + " успешно обновлена!");
        return "redirect:/moderator/species/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления расы {}", name);
        model.addAttribute("species", speciesService.findByName(name));
        return "species/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(SpeciesDTO species, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление расы {}", name);
        speciesService.delete(species);
        log.info("Раса {} успешно удалена!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Раса " + species.getName() + " успешно удалена!");
        return "redirect:/moderator/species/all";
    }
}
