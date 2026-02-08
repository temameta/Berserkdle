package org.example.berserkdle.dtos.deprecated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.berserkdle.utils.validation.UniqueName;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@SuperBuilder
public class SpeciesDTO extends AbstractDTO {
    @Override
    @UniqueName(entityClass = SpeciesDTO.class, message = "Такая раса уже существует!")
    @NotEmpty(message = "Название расы не может быть пустым!")
    @Size(min = 2, max = 32, message = "Название расы должно быть больше 2 и меньше 32 символов!")
    public String getName() {
        return super.getName();
    }
}
