package org.example.berserkdle.services;

import lombok.Getter;
import org.example.berserkdle.dtos.PersonDTO;
import org.example.berserkdle.dtos.RequestedPersonDto;
import org.example.berserkdle.dtos.ResultDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {
    private final PersonService personService;
    @Getter
    private PersonDTO hiddenPerson;

    public GameService(PersonService personService) {
        this.personService = personService;
        initHiddenPerson();
    }

    public void initHiddenPerson() {
        try {
            String name = Files.readString(Paths.get("../resources/static/HiddenPerson.txt"), StandardCharsets.UTF_8);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
        this.hiddenPerson = personService.findByName("Гатс");
    }

    public ResultDto compare(RequestedPersonDto requestedPerson) {
        PersonDTO personFromBase = personService.findByName(requestedPerson.getName());
        ResultDto resultDto = new ResultDto();

        RequestedPersonDto requestedPersonDto = new RequestedPersonDto();
        requestedPersonDto.setName(personFromBase.getName());
        requestedPersonDto.setGender(personFromBase.getGender());
        requestedPersonDto.setArc(personFromBase.getFirstArc());
        requestedPersonDto.setWeapons(personFromBase.getWeapons());
        requestedPersonDto.setGroups(personFromBase.getGroups());
        resultDto.setRequestedPersonDto(requestedPerson);

        if (personFromBase != null) {
            if (personFromBase.getFirstArc().equalsIgnoreCase(hiddenPerson.getFirstArc())) {
                resultDto.setArcMatch(ResultDto.MatchStatus.CORRECT);
            } else  {
                resultDto.setArcMatch(ResultDto.MatchStatus.WRONG);
            }
            if (personFromBase.getGender().equalsIgnoreCase(hiddenPerson.getGender())) {
                resultDto.setGenderMatch(ResultDto.MatchStatus.CORRECT);
            } else  {
                resultDto.setGenderMatch(ResultDto.MatchStatus.WRONG);
            }
            if (personFromBase.getSpecies().equalsIgnoreCase(hiddenPerson.getSpecies())) {
                resultDto.setSpeciesMatch(ResultDto.MatchStatus.CORRECT);
            } else  {
                resultDto.setSpeciesMatch(ResultDto.MatchStatus.WRONG);
            }
            resultDto.setWeaponMatch(checkMatch(personFromBase.getWeapons(), hiddenPerson.getWeapons()));
            resultDto.setGroupMatch(checkMatch(personFromBase.getGroups(), hiddenPerson.getGroups()));
        }
        return resultDto;
    }

    public static <T> ResultDto.MatchStatus checkMatch(List<T> list1, List<T> list2) {
        // Обработка null и пустых списков
        if (list1 == null || list2 == null || list1.isEmpty() || list2.isEmpty()) {
            return ResultDto.MatchStatus.WRONG;
        }

        // Создаем копии в виде Set для сравнения без учета порядка
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        // 1. Проверка на полное совпадение (без учета порядка)
        if (set1.equals(set2)) {
            return ResultDto.MatchStatus.CORRECT;
        }

        // 2. Проверка на частичное совпадение
        // Используем retainAll для поиска пересечения
        Set<T> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        if (!intersection.isEmpty()) {
            return ResultDto.MatchStatus.PARTIAL;
        }

        // 3. Если дошли сюда - нет совпадений
        return ResultDto.MatchStatus.WRONG;
    }
}
