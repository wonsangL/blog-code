package com.example.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NoSpecialCharacterValidator implements ConstraintValidator<NoSpecialCharacter, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        return !pattern.matcher(value).find();
    }
}
