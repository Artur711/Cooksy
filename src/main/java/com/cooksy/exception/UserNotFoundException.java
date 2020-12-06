package com.cooksy.exception;

import com.cooksy.service.UserId;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Id id) {
        super("User", id);
    }
}
