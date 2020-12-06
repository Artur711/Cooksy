package com.cooksy.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String rootCause, Id id) {
        super(String.format("%s not found for given id: %s", rootCause, id));
    }
}
