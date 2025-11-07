package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arcs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArcEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
}
