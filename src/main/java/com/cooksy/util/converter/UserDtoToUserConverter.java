package com.cooksy.util.converter;

import com.cooksy.dto.UserDto;
import com.cooksy.model.User;
import com.cooksy.model.UserType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoToUserConverter {

    public User convert(UserDto userDto) {
        UserType userType = new UserType();
        userType.setUserTypeId(userDto.getUserTypeId());

        return new User(userDto.getUserId(),
                userDto.getNick(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhotoUrl(),
                userType);
    }

    public List<User> convertAll(List<UserDto> usersDto) {
        return usersDto.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}


