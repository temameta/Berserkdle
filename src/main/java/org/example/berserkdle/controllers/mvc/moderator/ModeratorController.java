package org.example.berserkdle.controllers.mvc.moderator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ModeratorController {
    @GetMapping("/home")
    public String home() {
        return "moderator";
    }
}
