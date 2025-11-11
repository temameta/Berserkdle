package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "weapons")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WeaponEntity extends AbstractEntity {
    @OneToMany(mappedBy = "weapon")
    private List<PersonWithWeaponEntity> persons;
}
