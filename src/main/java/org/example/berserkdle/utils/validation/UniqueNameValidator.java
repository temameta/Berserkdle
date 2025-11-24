package org.example.berserkdle.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.berserkdle.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    private final ValidationService validationService;

    @Autowired
    public UniqueNameValidator(ValidationService validationService) {
        this.validationService = validationService;
    }

    private Class<?> entityClass;

    @Override
    public void initialize(UniqueName constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name == null || name.trim().isEmpty()) {
            return true;
        }
        return !validationService.isExist(entityClass.getSimpleName(), name);
    }
}
