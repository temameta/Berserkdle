package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(PersonWithWeaponId.class)
@Table(name = "persons_with_weapon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithWeaponEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @Id
    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private WeaponEntity weapon;
}
