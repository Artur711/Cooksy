package com.cooksy.util.comparator;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer integer1, Integer integer2) {
        return integer1.compareTo(integer2);
    }
}
