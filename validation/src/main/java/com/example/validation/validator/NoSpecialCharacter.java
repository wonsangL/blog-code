package com.example.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NoSpecialCharacterValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface NoSpecialCharacter {
    String message() default "can't contain special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
