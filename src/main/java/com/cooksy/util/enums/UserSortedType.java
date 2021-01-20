package com.cooksy.util.enums;

import com.cooksy.model.User;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;


public enum UserSortedType {

    USER_TYPE_SORT((user1, user2) -> user1.getUserType().getUserTypeName().compareTo(user1.getUserType().getUserTypeName())),
    FIRST_NAME_SORT(comparing(User::getFirstName)),
    LAST_NAME_SORT(comparing(User::getLastName));

    private final Comparator<User> sortTypeComparator;

    UserSortedType(Comparator<User> sortTypeComparator) {
        this.sortTypeComparator = sortTypeComparator;
    }

    public static boolean isContains(String str) {
        return Arrays.stream(values())
                .map(Enum::toString)
                .anyMatch(str::equals);
    }

    public Comparator<User> getSortTypeComparator() {
        return sortTypeComparator;
    }

}
