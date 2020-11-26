package com.cooksy.service;

import com.cooksy.dto.UserDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.User;
import com.cooksy.repository.UserRepository;

import com.cooksy.util.converter.UserDtoToUserConverter;
import com.cooksy.util.type.UserSortedType;
import com.cooksy.util.converter.UserToUserDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public List<UserDto> getSortedUsers(UserSortedType sortedType) {

        if (sortedType.equals(UserSortedType.FIRST_NAME)) {
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByFirstName());
        } else if (sortedType.equals(UserSortedType.LAST_NAME)) {
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByLastName());
        } else if (sortedType.equals(UserSortedType.TYPE)) {
            return userToUserDtoConverter.convertAll(userRepository.getSortedUserByUserType());
        }

        return getUsers();
    }

    public List<UserDto> getUsers() {
        return userToUserDtoConverter.convertAll((List<User>) userRepository.findAll());
    }

    public void addUser(UserDto userDto) {
        userRepository.save(userDtoToUserConverter.convert(userDto));
    }

    public void updateUser(Long userId, UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        user.setUserId(userId);
        userRepository.save(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserById(Long userId) {
        return userToUserDtoConverter.convert(
                userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId)));
    }
}
