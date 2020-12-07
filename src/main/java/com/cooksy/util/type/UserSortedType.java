package com.cooksy.util.type;

import java.util.Arrays;

public enum UserSortedType {
    USER_TYPE_SORT,
    FIRST_NAME_SORT,
    LAST_NAME_SORT;

    public static boolean isContains(String str) {
        return Arrays.stream(values())
                .map(Enum::toString)
                .anyMatch(str::equals);
    }
}