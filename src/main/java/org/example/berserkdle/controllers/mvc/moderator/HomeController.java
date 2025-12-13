package org.example.berserkdle.controllers.mvc.moderator;

import org.example.berserkdle.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moderator")
public class HomeController {
    private GameService gameService;

    @Autowired
    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("person", gameService.getHiddenPerson());
        return "moderator/home";
    }
}
