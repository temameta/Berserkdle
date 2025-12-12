package org.example.berserkdle.controllers.mvc.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.dtos.RequestedPersonDto;
import org.example.berserkdle.dtos.ResultDto;
import org.example.berserkdle.services.GameService;
import org.example.berserkdle.services.PersonService;
import org.example.berserkdle.services.PlayerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class GameController {
    private final PersonService personService;
    private final GameService gameService;
    private PlayerStatisticService statisticService;

    // Ключ для хранения загаданного персонажа в сессии
    private static final String SECRET_PERSON_KEY = "secretPerson";
    private static final String GUESSED_PERSONS_KEY = "guessedPersons";

    @Autowired
    public GameController(PlayerStatisticService statisticService, GameService gameService, PersonService personService) {
        this.statisticService = statisticService;
        this.gameService = gameService;
        this.personService = personService;
    }

    @ModelAttribute("personModel")
    public ResultDto initArc() {
        return new ResultDto();
    }

    @GetMapping
    public String mainPage(Model model, HttpSession session, @AuthenticationPrincipal UserDetails userDetails) {
        // Инициализируем игру, если еще не начата
        if (session.getAttribute(SECRET_PERSON_KEY) == null) {
            // Загадываем случайного персонажа
            session.setAttribute(SECRET_PERSON_KEY, gameService.getHiddenPerson());
            session.setAttribute(GUESSED_PERSONS_KEY, new ArrayList<ResultDto>());
            model.addAttribute("gameWon", false);
        }

        model.addAttribute("requestedPerson", new RequestedPersonDto());
        model.addAttribute("allPersonsNames", personService.getAllNames());

        System.out.println(session.getAttribute(GUESSED_PERSONS_KEY).toString());

        @SuppressWarnings("unchecked")
        List<ResultDto> guessedPersons = (List<ResultDto>) session.getAttribute(GUESSED_PERSONS_KEY);
        model.addAttribute("guessedPersons", guessedPersons);

        if (guessedPersons != null && !guessedPersons.isEmpty()) {
            ResultDto lastGuess = guessedPersons.get(guessedPersons.size() - 1);
            if (lastGuess.isFullyCorrect()) {
                model.addAttribute("gameWon", true);
                model.addAttribute("secretPersonName",
                        ((PersonDTO) session.getAttribute(SECRET_PERSON_KEY)).getName());
                // Если персонаж угадан

                int attempts = ((List<?>) session.getAttribute(GUESSED_PERSONS_KEY)).size(); // Получаем количество попыток в этой игре
                statisticService.updateOnWin(userDetails.getUsername(), attempts);

            }
        }

        return "user/main";
    }

    @PostMapping
    public String makeGuess(@ModelAttribute RequestedPersonDto requestedPersonDto,
                            HttpSession session,
                            @AuthenticationPrincipal UserDetails userDetails,
                            Model model) {

        PersonDTO secretPerson = (PersonDTO) session.getAttribute(SECRET_PERSON_KEY);

        if (secretPerson == null) {
            return "redirect:/";
        }

        ResultDto result = gameService.compare(requestedPersonDto);

        @SuppressWarnings("unchecked")
        List<ResultDto> guessedPersons = (List<ResultDto>) session.getAttribute(GUESSED_PERSONS_KEY);
        if (guessedPersons == null) {
            log.warn("Список угаданных персонажей пуст");
            guessedPersons = new ArrayList<>();
        }
        guessedPersons.add(result);
        session.setAttribute(GUESSED_PERSONS_KEY, guessedPersons);

        statisticService.addAttempt(userDetails.getUsername());

        return "redirect:/";
    }

    @PostMapping("/new-game")
    public String newGame(HttpSession session) {
        session.removeAttribute(SECRET_PERSON_KEY);
        session.removeAttribute(GUESSED_PERSONS_KEY);
        return "redirect:/";
    }
}
