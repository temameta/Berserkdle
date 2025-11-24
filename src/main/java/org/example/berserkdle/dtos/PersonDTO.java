package org.example.berserkdle.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.berserkdle.utils.validation.UniqueName;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonDTO extends AbstractDTO {
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

    @Override
    @UniqueName(entityClass = PersonDTO.class, message = "Такой персонаж уже существует!")
    @NotEmpty(message = "Имя персонажа не может быть пустым!")
    @Size(min = 2, max = 64, message = "Имя персонажа должно быть больше 2 и меньше 64 символов!")
    public String getName() {
        return super.getName();
    }

    public void addGroup(String groupName) {
        if (!this.groups.contains(groupName))
            this.groups.add(groupName);
    }

    public void addWeapon(String weaponName) {
        if (!this.weapons.contains(weaponName))
            this.weapons.add(weaponName);
    }
}
