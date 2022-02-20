package com.movies.common.excpetions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chahir Chalouati
 */
@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
