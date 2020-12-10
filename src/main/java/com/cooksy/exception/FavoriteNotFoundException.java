package com.cooksy.exception;

import com.cooksy.dto.Id;

public class FavoriteNotFoundException extends NotFoundException {

    public FavoriteNotFoundException(Id id) {
        super("Favorite", id);
    }
}
