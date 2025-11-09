package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
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
    private List<PersonWithGroupEntity> groups;
    @OneToMany(mappedBy = "person")
    private List<PersonWithWeaponEntity> weapons;
}
