package com.cooksy.exception;

public class ProductsToDbException extends RuntimeException{
    public ProductsToDbException(){
        super("There was problem with adding your products to DB");
    }
}
