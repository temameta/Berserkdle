package org.example.berserkdle.controllers.mvc.moderator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.services.GroupService;
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
@RequestMapping("/moderator/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        log.info("Инициализация контроллера GroupController");
        this.groupService = groupService;
    }

    @ModelAttribute("groupModel")
    public GroupDTO initGroup() {
        return new GroupDTO();
    }

    @GetMapping("/all")
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) String search,
            Model model
    ) {
        log.info("Открыта страница всех групп");
        if (search != null && !search.trim().isEmpty()) {
            log.info("Поиск группы");
            model.addAttribute("groups", groupService.findByName(search));
            model.addAttribute("search", search);
        } else {
            log.info("Вывод всех групп");
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            Page<GroupDTO> groupPage = groupService.allPaginated(pageable);

            model.addAttribute("groups", groupPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", groupPage.getTotalPages());
            model.addAttribute("totalItems", groupPage.getTotalElements());
        }
        return "group/get-all";
    }

    @GetMapping("/{name}")
    public String getGroup(@PathVariable String name, Model model) {
        log.info("Открыта персональная страница группы {}", name);
        model.addAttribute("group", groupService.findByName(name));
        return "group/group-page";
    }

    @GetMapping("/create")
    public String create() {
        log.info("Открыта страница создания новой группы");
        return "group/create";
    }

    @PostMapping("/create")
    public String create(@Valid GroupDTO groupModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Запрос на создание новой группы {}", groupModel.getName());
        if (bindingResult.hasErrors()) {
            log.error("Название группы {} некорректно", groupModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("groupModel", groupModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupModel", bindingResult);
            return "redirect:/moderator/group/create";
        }
        log.info("Создание новой группы {}...", groupModel.getName());
        groupService.save(groupModel);
        log.info("Группа успешно {} создана!", groupModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Группа " + groupModel.getName() + " успешно добавлена!");
        return "redirect:/moderator/group/create";
    }

    @GetMapping("/{name}/update")
    public String update(@PathVariable String name, Model model) {
        log.info("Открыта страница обновления группы {}", name);
        model.addAttribute("oldName", name);
        return "group/update";
    }

    @PostMapping("/{name}/update")
    public String update(@Valid GroupDTO groupModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на обновление группы {}", name);
        if (bindingResult.hasErrors()) {
            log.error("Название группы {} некорректно", groupModel.getName());
            log.error("Полный текст ошибок: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("groupModel", groupModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupModel", bindingResult);
            return "redirect:/moderator/group/{name}/update";
        }
        log.info("Обновление группы {}...", name);
        groupService.update(name, groupModel.getName());
        log.info("Группа {} успешно обновлена! Новое название: {}", name, groupModel.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Группа " + groupModel.getName() + " успешно обновлена!");
        return "redirect:/moderator/group/all";
    }

    @GetMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model) {
        log.info("Открыта страница удаления группы {}", name);
        model.addAttribute("group", groupService.findByName(name));
        return "group/delete";
    }

    @PostMapping("/{name}/delete")
    public String delete(GroupDTO group, RedirectAttributes redirectAttributes, @PathVariable String name) {
        log.info("Запрос на удаление группы {}", name);
        groupService.delete(group);
        log.info("Группа {} успешно удалена!", name);
        redirectAttributes.addFlashAttribute("successMessage", "Группа " + group.getName() + " успешно удалена!");
        return "redirect:/moderator/group/all";
    }
}
