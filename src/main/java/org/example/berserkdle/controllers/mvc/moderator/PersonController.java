package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.services.PersonService;
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
@RequestMapping("/moderator/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        log.info("Инициализация контроллера PersonController");
        this.personService = personService;
    }

    @ModelAttribute("personModel")
    public PersonDTO initPerson() {
        return new PersonDTO();
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
            log.info("Поиск персонажа");
            model.addAttribute("persons", personService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех полов");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<PersonDTO> personPage = personService.allPaginated(pageable);

            model.addAttribute("persons", personPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", personPage.getTotalPages());
            model.addAttribute("totalItems", personPage.getTotalElements());
        }
        return "person/get-all";
    }

    @GetMapping("/{name}")
    public String getPerson(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница персонажа {}", name);
        model.addAttribute("person", personService.findByName(name));
        System.out.println(personService.findByName(name));
        return "person/person-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания новой персонажа");
        return "person/create";
    }

    @PostMapping("/create")
    public String create(@Valid PersonDTO personModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание новой персонажа {}", personModel.getName());
        System.out.println(personModel);
        if (bindingResult.hasErrors()) {
            log.error("Название персонажа {} некорректно", personModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("personModel", personModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.personModel", bindingResult);
            return "redirect:/moderator/person/create";
        }
        log.info("Создание новой персонажа {}...", personModel.getName());
        personService.save(personModel);
        log.info("Пол успешно {} добавлен!", personModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + personModel.getName() + " успешно добавлен!");
        return "redirect:/moderator/person/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления персонажа {}", name);
        model.addAttribute("oldName", name);
        return "person/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid PersonDTO personModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление персонажа {}", name);
        if (bindingResult.hasErrors()) {
            log.error("Название персонажа {} некорректно", personModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("personModel", personModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.personModel", bindingResult);
            return "redirect:/moderator/person/{name}/update";
        }
        log.info("Обновление персонажа {}...", name);
        personService.update(name, personModel.getName());
        log.info("Пол {} успешно добавлен! Новое название: {}", name, personModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + personModel.getName() + " успешно добавлен!");
        return "redirect:/moderator/person/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления персонажа {}", name);
        model.addAttribute("person", personService.findByName(name));
        return "person/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(PersonDTO person, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление персонажа {}", name);
        personService.delete(person);
        log.info("Пол {} успешно добавлен!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Пол " + person.getName() + " успешно добавлен!");
        return "redirect:/moderator/person/all";
    }
}
