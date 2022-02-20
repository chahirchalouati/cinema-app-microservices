package com.movies.common.validations.annotations;

import com.movies.common.validations.Validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chahir Chalouati
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {

    String message() default "Invalid password format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
