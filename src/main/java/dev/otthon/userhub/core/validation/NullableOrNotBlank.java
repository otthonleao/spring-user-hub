package dev.otthon.userhub.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullableOrNotBlankValidator.class)
public @interface NullableOrNotBlank {

    String message() default "Field must be null but not blank or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
