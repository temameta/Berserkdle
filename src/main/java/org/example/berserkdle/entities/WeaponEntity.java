package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "weapons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "weapon")
    private List<PersonWithWeaponEntity> persons;
}
