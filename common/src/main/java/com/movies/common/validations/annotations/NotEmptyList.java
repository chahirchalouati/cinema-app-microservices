package com.movies.common.validations.annotations;

import com.movies.common.validations.Validators.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = NotEmptyListValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyList {
    String message() default "list is empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
