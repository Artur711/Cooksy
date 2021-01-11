package com.cooksy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;

    @JsonProperty("username")
    private String nick;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photoUrl;
    private Long userTypeId;
}
