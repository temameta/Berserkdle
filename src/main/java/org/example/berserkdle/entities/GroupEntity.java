package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class GroupEntity extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "group")
    private List<PersonWithGroupEntity> persons;
}
