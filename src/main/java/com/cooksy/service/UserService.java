package com.cooksy.service;

import com.cooksy.dto.UserDto;
import com.cooksy.model.User;
import com.cooksy.repository.UserRepository;
import com.cooksy.util.UserDtoToUserConverter;
import com.cooksy.util.UserToUserDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public List<UserDto> getAll() {
        return userToUserDtoConverter.convertAll((List<User>) userRepository.findAll());
    }

    public void saveUser(UserDto userDto) {
        userRepository.save(userDtoToUserConverter.convert(userDto));
    }

    public void deleteUser(UserDto userDto) {
        userRepository.delete(userDtoToUserConverter.convert(userDto));
    }

    public UserDto getUserById(Long userId) {

        if (userRepository.findById(userId).isPresent()) {
            return userToUserDtoConverter.convert(userRepository.findById(userId).get());
        }
        return null;
    }
}
