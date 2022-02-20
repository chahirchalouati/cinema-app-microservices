package com.movies.common.validations.Validators;

import com.movies.common.validations.annotations.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @author Chahir Chalouati
 */
public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(pattern);
    }

}
