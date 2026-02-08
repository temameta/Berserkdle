package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.Setter;
import org.example.berserkdle.enums.UserRoles;

import java.io.Serializable;


@Entity
@Table(name = "roles")
public class Role extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private UserRoles name;

    public Role(UserRoles name) {
        this.name = name;
    }

    public Role() {

    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public UserRoles getName() {
        return name;
    }

}