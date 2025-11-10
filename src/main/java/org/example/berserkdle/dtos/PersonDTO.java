package org.example.berserkdle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String firstArc;
    private String gender;
    private String species;
    private List<String> groups;
    private List<String> weapons;
}
