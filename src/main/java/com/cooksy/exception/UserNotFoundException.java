package com.cooksy.exception;


import com.cooksy.dto.Id;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Id id) {
        super("User", id);
    }
}
