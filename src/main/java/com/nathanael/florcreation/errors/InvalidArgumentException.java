package com.nathanael.florcreation.errors;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String cause) {
        super(cause);
    }
}
