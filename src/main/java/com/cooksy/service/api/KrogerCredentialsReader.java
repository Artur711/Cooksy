package com.cooksy.service.api;

import com.cooksy.service.FileReaderTxt;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class KrogerCredentialsReader {

    private final List<String> credentials;

    public KrogerCredentialsReader() {
        this.credentials = new FileReaderTxt("kroger-credentials.txt").getTextFromFile();
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
}
