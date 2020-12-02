package com.cooksy.exception;

public class FavoriteNotFoundException extends RuntimeException{

    public FavoriteNotFoundException(Long id) {
        super("Favorite not found for given id: " + id);
    }
}
