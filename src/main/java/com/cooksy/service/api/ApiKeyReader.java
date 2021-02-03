package com.cooksy.service.api;

import com.cooksy.service.FileReaderTxt;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApiKeyReader {

    private final List<String> apiKeys;
    private int numberOfKey = 0;

    public ApiKeyReader() {
        this.apiKeys = new FileReaderTxt("api-key.txt").getTextFromFile();
    }

    public String getKey() {
        return this.apiKeys.get(numberOfKey);
    }

    public void next() {
        if (numberOfKey + 1 != apiKeys.size()) {
            this.numberOfKey++;
        }
        else {
            this.numberOfKey = 0;
        }
    }
}
