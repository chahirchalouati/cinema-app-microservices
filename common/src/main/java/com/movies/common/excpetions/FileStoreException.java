package com.movies.common.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Chahir Chalouati
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStoreException extends RuntimeException {
    public FileStoreException(String message) {
        super(message);
    }
}
