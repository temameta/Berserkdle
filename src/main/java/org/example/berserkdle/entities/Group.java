package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, nullable = false)
    String name;

    public Group(String name) {
        this.name = name;
    }
}
