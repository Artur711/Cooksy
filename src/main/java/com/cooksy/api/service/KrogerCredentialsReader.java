package com.cooksy.api.service;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class KrogerCredentialsReader {

    private final List<String> credentials;

    public KrogerCredentialsReader() {
        this.credentials = this.getCredentials();
    }

    public String getClientId() {
        return this.credentials.get(0);
    }

    public String getClientSecret() {
        return this.credentials.get(2);
    }

    public String getScope() {
        return this.credentials.get(1);
    }

    private List<String> getCredentials() {
        List<String> credentials = new ArrayList<>();

        try {
            Scanner inFile = new Scanner(new FileReader("./src/main/resources/kroger-credentials.txt"));
            while (inFile.hasNextLine()) {
                credentials.add(inFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("kroger-credentials.txt file not found");
        }
        return credentials;
    }
}
