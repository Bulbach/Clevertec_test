package org.example.exceptions;

public class ApplicationBaseException extends RuntimeException{
    public ApplicationBaseException() {
    }

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
