package com.cooksy.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found for given id: " + id);
    }
}
