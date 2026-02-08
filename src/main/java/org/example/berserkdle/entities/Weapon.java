package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "weapons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    String name;

    public Weapon(String name) {
        this.name = name;
    }
}
