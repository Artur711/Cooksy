package com.cooksy.exception;

import com.cooksy.dto.Id;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String rootCause, Id id) {
        super(String.format("%s not found for given id: %s", rootCause, id.getValue()));
    }
    public NotFoundException(String rootCause) {
        super(String.format("%s not found for given id", rootCause));
    }
}
