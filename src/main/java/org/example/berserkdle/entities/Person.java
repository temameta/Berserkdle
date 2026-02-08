package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@Getter
public class Person extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String gender;
    @Column(unique = true, nullable = false)
    private String arc;
    @Column(unique = true, nullable = false)
    private String species;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Group> groups;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Weapon> weapons;

    public Person(String name, String gender, String arc, String species, List<Group> groups, List<Weapon> weapons) {
        this.name = name;
        this.gender = gender;
        this.arc = arc;
        this.species = species;
        this.groups = groups;
        this.weapons = weapons;
    }
}
