package com.cooksy.service;

import static java.lang.Long.parseLong;

public class UserId {

    private final long value;

    private UserId(long value) {
        this.value = value;
    }

    public static UserId idFromString(String value) {
        return new UserId(parseLong(value));
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value=" + value +
                '}';
    }
}
