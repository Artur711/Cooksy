package com.cooksy.service;

import com.cooksy.dto.UserDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.User;
import com.cooksy.repository.UserRepository;
import com.cooksy.util.comparator.StringComparator;
import com.cooksy.util.comparator.IntegerComparator;
import com.cooksy.util.converter.UserDtoToUserConverter;
import com.cooksy.util.type.UserSortedType;
import com.cooksy.util.converter.UserToUserDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {

    private final IntegerComparator integerComparator;
    private final StringComparator stringComparator;
    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public List<UserDto> getSortedUsers(UserSortedType sortedType) {
        List<UserDto> usersDto = getUsers();

        if (sortedType.equals(UserSortedType.FIRST_NAME)) {
            return usersDto.stream()
                    .sorted((usersDto1, userDto2) -> stringComparator
                            .compare(usersDto1.getFirstName(), userDto2.getFirstName()))
                    .collect(Collectors.toList());
        } else if (sortedType.equals(UserSortedType.LAST_NAME)) {
            return usersDto.stream()
                    .sorted((usersDto1, usersDto2) -> stringComparator
                            .compare(usersDto1.getLastName(), usersDto2.getLastName()))
                    .collect(Collectors.toList());
        } else if (sortedType.equals(UserSortedType.TYPE)) {
            return usersDto.stream()
                    .sorted((usersDto1, usersDto2) -> integerComparator
                            .compare(usersDto1.getUserType(), usersDto2.getUserType()))
                    .collect(Collectors.toList());
        }

        return usersDto;
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
