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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GroupEntity extends AbstractEntity {
    @OneToMany(mappedBy = "group")
    private List<PersonWithGroupEntity> persons;
}
