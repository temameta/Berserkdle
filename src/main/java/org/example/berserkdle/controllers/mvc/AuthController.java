package org.example.berserkdle.controllers.mvc;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.UserRegistrationDto;
import org.example.berserkdle.entities.PlayerStatistic;
import org.example.berserkdle.entities.User;
import org.example.berserkdle.enums.UserRoles;
import org.example.berserkdle.services.AuthService;
import org.example.berserkdle.services.PlayerStatisticService;
import org.example.berserkdle.view.UserProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/users")
public class AuthController {
    private final PlayerStatisticService statisticService;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService, PlayerStatisticService statisticService) {
        this.authService = authService;
        this.statisticService = statisticService;
        log.info("AuthController инициализирован");
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + UserRoles.MODERATOR.name()))) {
            return "redirect:/moderator";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        log.debug("Отображение страницы регистрации");
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.debug("Обработка регистрации пользователя: {}", userRegistrationDto.getUsername());
        
        if (bindingResult.hasErrors()) {
            log.warn("Ошибки валидации при регистрации: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegistrationDto);
        log.info("Пользователь успешно зарегистрирован: {}", userRegistrationDto.getUsername());

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        log.debug("Отображение страницы входа");
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        
        log.warn("Неудачная попытка входа для пользователя: {}", username);
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        log.debug("Отображение профиля пользователя: {}", username);
        PlayerStatistic stats = statisticService.getStatistics(username);

        model.addAttribute("winRate",
                stats.getTotalGamesPlayed() > 0 ?
                        (double) stats.getGamesWon() / stats.getTotalGamesPlayed() * 100 : 0);

        User user = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getEmail(),
                stats.getGamesWon(),
                stats.getTotalAttempts(),
                stats.getTotalGamesPlayed(),
                stats.getTotalGamesPlayed() > 0 ? (double) stats.getGamesWon() / stats.getTotalGamesPlayed() * 100 : 0
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
