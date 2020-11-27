package com.cooksy.dto;

import com.cooksy.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photoUrl;
    private long userTypeId;
}
