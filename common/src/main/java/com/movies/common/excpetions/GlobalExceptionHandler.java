package com.movies.common.excpetions;

import com.movies.movieservice.DTOs.Responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Chahir Chalouati
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> notFoundResponse(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequestResponse(MethodArgumentNotValidException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> bindException(BindException exception) {
        final Map<String, String> errorsMap = exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(new ErrorResponse(errorsMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FileStoreException.class})
    public ResponseEntity<?> fileStoreError(FileStoreException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FileFormatNotAcceptedException.class})
    public ResponseEntity<?> badRequest(FileFormatNotAcceptedException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SignatureException.class, UnauthorizedException.class})
    public ResponseEntity<String> unauthorizedResponse() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
