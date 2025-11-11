package org.example.berserkdle.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonDTO extends AbstractDTO {
    private String firstArc;
    private String gender;
    private String species;
    private List<String> groups = new ArrayList<>();
    private List<String> weapons = new ArrayList<>();

    public void addGroup(String groupName) {
        if (!this.groups.contains(groupName))
            this.groups.add(groupName);
    }

    public void addWeapon(String weaponName) {
        if (!this.weapons.contains(weaponName))
            this.weapons.add(weaponName);
    }
}
