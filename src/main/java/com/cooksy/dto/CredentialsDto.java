package com.cooksy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CredentialsDto {

    @JsonProperty("username")
    private String nick;

    private String password;
}
