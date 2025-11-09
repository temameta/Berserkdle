package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons_with_weapon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithWeaponEntity {
    @EmbeddedId
    private PersonWithWeaponId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @MapsId("weaponId")
    @JoinColumn(name = "weapon_id")
    private WeaponEntity weapon;
}
