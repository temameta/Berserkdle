package org.example.berserkdle.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractDTO {
    @NotEmpty(message = "Название не может быть пустым!")
    @Size(min = 3, message = "Название должно содержать не менее 3 символов!")
    private String name;
}
