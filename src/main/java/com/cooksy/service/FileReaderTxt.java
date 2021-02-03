package com.cooksy.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderTxt {

    private final String FILE_NAME;

    public FileReaderTxt(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public List<String> getTextFromFile() {
        List<String> credentials = new ArrayList<>();

        try {
            Scanner inFile = new Scanner(new java.io.FileReader(String.format("./src/main/resources/%s", FILE_NAME)));
            while (inFile.hasNextLine()) {
                credentials.add(inFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("%s file not found%n\n", FILE_NAME);
        }
        return credentials;
    }
}
