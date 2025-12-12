package org.example.berserkdle.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestedPersonDto {
    private String name;
    private String gender;
    private List<String> groups;
    private String species;
    private List<String> weapons;
    private String arc;
}
