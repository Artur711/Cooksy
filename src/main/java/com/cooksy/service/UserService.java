package com.cooksy.service;

import com.cooksy.dto.CredentialsDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.UserDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.User;
import com.cooksy.model.UserType;
import com.cooksy.repository.UserRepository;

import com.cooksy.util.converter.UserDtoToUserConverter;
import com.cooksy.util.converter.UserToUserDtoConverter;
import com.cooksy.util.enums.UserSortedType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

import static com.cooksy.util.enums.UserSortedType.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public UserService(UserRepository userRepository, UserToUserDtoConverter userToUserDtoConverter, UserDtoToUserConverter userDtoToUserConverter) {
        this.userRepository = userRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    public void register(UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        failIfUserAlreadyRegistered(user.getFirstName());
        userRepository.save(user);
    }

    public Authentication login(CredentialsDto credentialsDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDto.getNick(),
                credentialsDto.getPassword()));
    }

    public List<UserDto> getSortedUsers(UserSortedType sortedType) {
        List<User> users;

        if (sortedType.equals(FIRST_NAME_SORT)) {
            users = userRepository.getSortedUserByFirstName();
            log.info("Returned sorted users by first name");
        }
        else if (sortedType.equals(LAST_NAME_SORT)) {
            users = userRepository.getSortedUserByLastName();
            log.info("Returned sorted users by last name");
        } else if (sortedType.equals(USER_TYPE_SORT)) {
            users = userRepository.getSortedUserByUserType();
            log.info("Returned sorted users by type from administrators");
        } else {
            users = (List<User>) userRepository.findAll();
            log.info("Returns users not sorted");
        }
        return userToUserDtoConverter.convertAll(users);
    }

    public List<UserDto> getUsers() {
        List<UserDto> usersDto  = userToUserDtoConverter.convertAll((List<User>) userRepository.findAll());
        log.info("Returned all users from database");
        return usersDto;
    }

    public List<UserDto> getUsersByTypeId(Id id) {
        UserType userType = new UserType();
        userType.setUserTypeId(id.getValue());
        List<UserDto> usersDto = userToUserDtoConverter.convertAll(userRepository.findByUserType(userType));
        log.info(String.format("Returned all users by type: %d from database", id.getValue()));
        return usersDto;
    }

    public void addUser(UserDto userDto) {
        log.info(String.format("Starts save user: %s", userDto.toString()));
        User user = userDtoToUserConverter.convert(userDto);
        userRepository.save(user);
    }

    public void updateUser(Id id, UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        user.setUserId(id.getValue());
        log.info(String.format("Starts save user: %s", user.toString()));
        userRepository.save(user);
    }

    public void removeUser(Id id) {
        log.info(String.format("Starts delete user by id: %d", id.getValue()));
        userRepository.deleteById(id.getValue());
    }

    public UserDto getUserById(Id id) {
        UserDto userDto = userToUserDtoConverter.convert(
                userRepository.findById(id.getValue())
                        .orElseThrow(() -> new UserNotFoundException(id)));

        log.info(String.format("Returned user by id: %d", id.getValue()));
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByNick(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with nick %s cannot be found.", userName)));
    }

    private void failIfUserAlreadyRegistered(String userName) {
        Optional<User> maybeUser = userRepository.findByNick(userName);
        if (maybeUser.isPresent()) {
            throw new ValidationException("User already exist: " + maybeUser.get().getFirstName());
        }
    }
}
