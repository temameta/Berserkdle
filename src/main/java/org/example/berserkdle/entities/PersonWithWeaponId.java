package org.example.berserkdle.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithWeaponId implements Serializable {
    private Long personId;
    private Long weaponId;


}
