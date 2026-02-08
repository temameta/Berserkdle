package org.example.berserkdle.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.berserkdle.utils.validation.UniqueName;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonDTO {
    private String name;
    @NotEmpty(message = "Арка первого появления должна быть заполнена!")
    private String firstArc;
    @NotEmpty(message = "Пол должен быть заполнен!")
    private String gender;
    @NotEmpty(message = "Раса должна быть заполнена!")
    private String species;
    @NotEmpty(message = "Группы не могут быть пустыми!")
    private List<String> groups = new ArrayList<>();
    @NotEmpty(message = "Оружия не могут быть пустыми!")
    private List<String> weapons = new ArrayList<>();

    @UniqueName(entityClass = PersonDTO.class, message = "Такой персонаж уже существует!")
    @NotEmpty(message = "Имя персонажа не может быть пустым!")
    @Size(min = 2, max = 64, message = "Имя персонажа должно быть от 2 до 64 символов!")
    public String getName() {
        return this.name;
    }
}
