package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons_with_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithGroupEntity {
    @EmbeddedId
    private PersonWithGroupId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
