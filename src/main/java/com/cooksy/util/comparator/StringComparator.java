package com.cooksy.util.comparator;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class StringComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
