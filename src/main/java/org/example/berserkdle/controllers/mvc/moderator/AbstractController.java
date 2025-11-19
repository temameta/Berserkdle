package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.dtos.AbstractDTO;
import org.example.berserkdle.entities.AbstractEntity;
import org.example.berserkdle.services.AbstractService;
import org.example.berserkdle.services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractController<D extends AbstractDTO, E extends AbstractEntity, S extends InterfaceService<D, E>> implements InterfaceController<D> {
    protected final S service;

    @Autowired
    public AbstractController(S service) {
        this.service = service;
    }

    @Override
    public String create(D dto, RedirectAttributes redirectAttributes) {
        service.save(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Сущность " + dto.getName() + " успешно добавлена!");
        return "redirect:../";
    }
}
