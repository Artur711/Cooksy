package com.cooksy.service;

import com.cooksy.dto.UserDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.User;
import com.cooksy.model.UserType;
import com.cooksy.repository.UserRepository;

import com.cooksy.util.converter.UserDtoToUserConverter;
import com.cooksy.util.type.UserSortedType;
import com.cooksy.util.converter.UserToUserDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public List<UserDto> getSortedUsers(UserSortedType sortedType) {

        if (sortedType.equals(UserSortedType.FIRST_NAME)) {
            log.info("Starts sort users by first name");
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByFirstName());
        } else if (sortedType.equals(UserSortedType.LAST_NAME)) {
            log.info("Starts sort users by last name");
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByLastName());
        } else if (sortedType.equals(UserSortedType.TYPE)) {
            log.info("Starts sort users by type from administrators");
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByUserType());
        }


        userToUserDtoConverter.convertAll();

        log.info("Returns users not sorted");

        return getUsers();
    }

    public List<UserDto> getUsers() {
        log.info("Starts getting all users from database");
        return userToUserDtoConverter.convertAll((List<User>) userRepository.findAll());
    }

    public List<UserDto> getUsersByTypeId(Long typeId) {
        UserType userType = new UserType();
        userType.setUserTypeId(typeId);
        log.info(String.format("Starts getting all users by type: %d from database", typeId));
        return userToUserDtoConverter.convertAll(userRepository.findByUserType(userType));
    }

    public void addUser(UserDto userDto) {
        log.info(String.format("Starts save user: %s", userDto.toString()));
        User user = userDtoToUserConverter.convert(userDto);
        userRepository.save(user);
    }

    public void updateUser(Long userId, UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        user.setUserId(userId);
        log.info(String.format("Starts save user: %s", user.toString()));
        userRepository.save(user);
    }

    public void removeUser(Long userId) {
        log.info(String.format("Starts delete user by id: %d", userId));
        userRepository.deleteById(userId);
    }

    public UserDto getUserById(Long userId) {
        log.info(String.format("Starts find user by id: %d", userId));
        return userToUserDtoConverter.convert(
                userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId)));
    }
}
