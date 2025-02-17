package dev.otthon.userhub.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullableOrNotBlankValidator implements ConstraintValidator<NullableOrNotBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else if (value.isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(NullableOrNotBlank constraintAnnotation) {
        // Inicialização dos argumentos (não é necessária para este caso específico)
    }
}
