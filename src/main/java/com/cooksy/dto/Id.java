package com.cooksy.dto;

import static java.lang.Long.parseLong;

public class Id {

    private final Long value;

    private Id(Long value) {
        this.value = value;
    }

    public static Id idFromString(String value) {
        return new Id(parseLong(value));
    }

    public static Id idFromLong(Long value) {
        return new Id(value);
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Id{" +
                "value=" + value +
                '}';
    }
}
