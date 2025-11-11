package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.berserkdle.dtos.PersonDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "first_arc_id", nullable = false)
    private ArcEntity firstArc;
    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;
    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private SpeciesEntity species;
    @OneToMany(mappedBy = "person")
    private List<PersonWithGroupEntity> groups = new ArrayList<>();
    @OneToMany(mappedBy = "person")
    private List<PersonWithWeaponEntity> weapons = new ArrayList<>();

    public void addGroup(PersonWithGroupEntity personWithGroupEntity) {
        if (!this.groups.contains(personWithGroupEntity))
            this.groups.add(personWithGroupEntity);
    }

    public void addWeapon(PersonWithWeaponEntity personWithWeaponEntity) {
        if (!this.weapons.contains(personWithWeaponEntity))
            this.weapons.add(personWithWeaponEntity);
    }
}
