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
public class ArcDTO extends AbstractDTO {
    @Override
    @UniqueName(entityClass = ArcDTO.class, message = "Такая арка уже существует!")
    @NotEmpty(message = "Название арки не может быть пустым!")
    @Size(min = 2, max = 128, message = "Название арки должно быть больше 2 и меньше 128 символов!")
    public String getName() {
        return super.getName();
    }
}
