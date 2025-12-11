package org.example.berserkdle.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestedPersonDto {
    private String name = null;
    private Map<String, Boolean> params = null;

}
