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
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String arc;
    @Column(nullable = false)
    private String species;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Group> groups;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
