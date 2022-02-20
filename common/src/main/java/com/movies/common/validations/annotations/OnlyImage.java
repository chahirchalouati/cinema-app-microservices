package com.movies.common.validations.annotations;

import com.movies.common.validations.Validators.OnlyImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = OnlyImageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyImage {

    String message() default "file should be an image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
