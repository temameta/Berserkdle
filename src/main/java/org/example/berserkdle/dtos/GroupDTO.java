package org.example.berserkdle.dtos;

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
public class GroupDTO extends AbstractDTO {
    @Override
    @UniqueName(entityClass = GroupDTO.class, message = "Такая группа уже существует!")
    @NotEmpty(message = "Название группы не может быть пустым!")
    @Size(min = 2, max = 32, message = "Название пола должно быть больше 2 и меньше 32 символов!")
    public String getName() {
        return super.getName();
    }
}
