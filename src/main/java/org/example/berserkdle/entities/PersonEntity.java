package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "characters")
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
    @JoinColumn(name = "id", nullable = false)
    private ArcEntity firstArc;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private GenderEntity gender;
    @ManyToMany
    //@JoinColumn(name = "id", nullable = false)
    private List<GroupEntity> groups;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private SpeciesEntity species;
    @OneToMany(mappedBy = "person")
    private List<PersonWithWeaponEntity> weapons;
}
