package org.example.berserkdle.controllers.mvc.user;

import org.example.berserkdle.dtos.ArcDTO;
import org.example.berserkdle.dtos.GameView;
import org.example.berserkdle.dtos.RequestedPersonDto;
import org.example.berserkdle.services.GameService;
import org.example.berserkdle.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class GameController {
    private final PersonService personService;
    private final GameService gameService;

    @Autowired
    public GameController(PersonService personService, GameService gameService) {
        this.personService = personService;
        this.gameService = gameService;
    }

    @ModelAttribute("personModel")
    public RequestedPersonDto initArc() {
        return new RequestedPersonDto();
    }

    // 1. пустая модель
    @GetMapping
    public String mainPage() {
        return "user/main";
    }

    // 2. модель с запрошенным персонажем
    @PostMapping
    public String mainPage(RequestedPersonDto requestedPersonDto, Model model) {
        model.addAttribute("personModel", gameService.compare(requestedPersonDto));
        return "redirect:/";
    }
}
