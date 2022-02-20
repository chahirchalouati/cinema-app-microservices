package com.movies.common.excpetions;

/**
 * @author Chahir Chalouati
 */
public class FileFormatNotAcceptedException extends RuntimeException {
    public FileFormatNotAcceptedException() {
    }

    public FileFormatNotAcceptedException(String message) {
        super(message);
    }

    public FileFormatNotAcceptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileFormatNotAcceptedException(Throwable cause) {
        super(cause);
    }
}
