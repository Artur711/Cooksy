package com.cooksy.exception;

public class FavoriteNotFoundException extends NotFoundException {

    public FavoriteNotFoundException(Id id) {
        super("Favorite", id);
    }
}
