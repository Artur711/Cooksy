package com.cooksy.service;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ApiKeyReader {

    public List<String> getKeys() {
        List<String> apiKeys = new ArrayList<>();

        try {
            Scanner inFile = new Scanner(new FileReader("./src/main/resources/api-key.txt"));
            while (inFile.hasNextLine()) {
                apiKeys.add(inFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
        }

        return apiKeys;
    }
}
