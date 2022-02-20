package com.movies.common.validations.Validators;

import com.movies.common.validations.annotations.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
/**
 * @author Chahir Chalouati
 */
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, Collection> {

    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext constraintValidatorContext) {
        return !collection.isEmpty();
    }
}
