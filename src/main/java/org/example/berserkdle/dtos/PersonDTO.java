package org.example.berserkdle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
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
