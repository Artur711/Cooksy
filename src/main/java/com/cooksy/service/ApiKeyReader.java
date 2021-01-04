package com.cooksy.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ApiKeyReader {

    private final List<String> apiKeys = new ArrayList<>();

    public List<String> getKey() {

        File myObj = new File("../resources/api-key.txt");
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            apiKeys.add(myReader.nextLine());
        }
        return apiKeys;
    }
}
