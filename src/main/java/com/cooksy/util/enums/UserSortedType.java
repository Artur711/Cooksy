package com.cooksy.util.enums;

import com.cooksy.model.User;

import java.util.Comparator;

import static java.util.Comparator.comparing;

public enum UserSortedType {

    USER_TYPE_SORT((user2, user1) -> user1.getUserType().getUserTypeName().
            compareTo(user1.getUserType().getUserTypeName())),
    FIRST_NAME_SORT(comparing(User::getFirstName)),
    LAST_NAME_SORT(comparing(User::getLastName));

    private final Comparator<User> sortTypeComparator;

    UserSortedType(Comparator<User> sortTypeComparator) {
        this.sortTypeComparator = sortTypeComparator;
    }

    public Comparator<User> getSortTypeComparator() {
        return sortTypeComparator;
    }
}
