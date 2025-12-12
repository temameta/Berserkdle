package org.example.berserkdle.controllers.mvc.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.dtos.RequestedPersonDto;
import org.example.berserkdle.dtos.ResultDto;
import org.example.berserkdle.services.GameService;
import org.example.berserkdle.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Ключ для хранения загаданного персонажа в сессии
    private static final String SECRET_PERSON_KEY = "secretPerson";
    private static final String GUESSED_PERSONS_KEY = "guessedPersons";

    @Autowired
    public GameController(PersonService personService, GameService gameService) {
        this.personService = personService;
        this.gameService = gameService;
    }

    @ModelAttribute("personModel")
    public ResultDto initArc() {
        return new ResultDto();
    }

    @GetMapping
    public String mainPage(Model model, HttpSession session) {
        // Инициализируем игру, если еще не начата
        if (session.getAttribute(SECRET_PERSON_KEY) == null) {
            // Загадываем случайного персонажа
            session.setAttribute(SECRET_PERSON_KEY, gameService.getHiddenPerson());
            session.setAttribute(GUESSED_PERSONS_KEY, new ArrayList<ResultDto>());
        }

        model.addAttribute("requestedPerson", new RequestedPersonDto());
        model.addAttribute("allPersonsNames", personService.getAllNames());
        model.addAttribute("gameWon", false);

        System.out.println(session.getAttribute(GUESSED_PERSONS_KEY).toString());

        // Получаем историю догадок
        @SuppressWarnings("unchecked")
        List<ResultDto> guessedPersons = (List<ResultDto>) session.getAttribute(GUESSED_PERSONS_KEY);
        model.addAttribute("guessedPersons", guessedPersons);

        // Проверяем, угадан ли персонаж
        if (guessedPersons != null && !guessedPersons.isEmpty()) {
            ResultDto lastGuess = guessedPersons.get(guessedPersons.size() - 1);
            if (lastGuess.isFullyCorrect()) {
                model.addAttribute("gameWon", true);
                model.addAttribute("secretPersonName",
                        ((PersonDTO) session.getAttribute(SECRET_PERSON_KEY)).getName());
            }
        }

        return "user/main";
    }

    @PostMapping
    public String makeGuess(@ModelAttribute RequestedPersonDto requestedPersonDto,
                            HttpSession session,
                            Model model) {

        PersonDTO secretPerson = (PersonDTO) session.getAttribute(SECRET_PERSON_KEY);

        if (secretPerson == null) {
            return "redirect:/";
        }

        // Сравниваем введенного персонажа с загаданным
        ResultDto result = gameService.compare(requestedPersonDto);

        // Добавляем результат в историю
        @SuppressWarnings("unchecked")
        List<ResultDto> guessedPersons = (List<ResultDto>) session.getAttribute(GUESSED_PERSONS_KEY);
        if (guessedPersons == null) {
            log.warn("Список угаданных персонажей пуст");
            guessedPersons = new ArrayList<>();
        }
        guessedPersons.add(result);
        session.setAttribute(GUESSED_PERSONS_KEY, guessedPersons);

        return "redirect:/";
    }

    @PostMapping("/new-game")
    public String newGame(HttpSession session) {
        // Очищаем сессию и начинаем новую игру
        session.removeAttribute(SECRET_PERSON_KEY);
        session.removeAttribute(GUESSED_PERSONS_KEY);
        return "redirect:/";
    }
}
