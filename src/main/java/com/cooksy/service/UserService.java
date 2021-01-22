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
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
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
        List<User> users = sortBy(sortedType);
        log.info("Returns sorted users by" + sortedType.name().toLowerCase());
        return userToUserDtoConverter.convertAll(users);
    }

    private List<User> sortBy(UserSortedType userSortedType) {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList.stream()
                .sorted(userSortedType.getSortTypeComparator())
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsers() {
        List<UserDto> usersDto = userToUserDtoConverter.convertAll((List<User>) userRepository.findAll());
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
        User user = userDtoToUserConverter.convert(userDto);
        User savedUser = userRepository.save(user);
        log.info(String.format("Saved user [id: %s]", savedUser.getUserId()));
    }

    public void updateUser(Id id, UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        user.setUserId(id.getValue());
        User updatedUser = userRepository.save(user);
        log.info(String.format("Updated user [id: %s]", updatedUser.getUserId()));
    }

    public void removeUser(Id id) {
        userRepository.deleteById(id.getValue());
        log.info(String.format("Deleted user [id: %d]", id.getValue()));
    }

    public UserDto getUserById(Id id) {
        UserDto userDto = userToUserDtoConverter.convert(
                userRepository.findById(id.getValue())
                        .orElseThrow(() -> new UserNotFoundException(id)));

        log.info(String.format("Returned user [id: %d]", id.getValue()));
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with nick %s cannot be found.", userName)));
    }

    private void failIfUserAlreadyRegistered(String userName) {
        Optional<User> maybeUser = userRepository.findByName(userName);
        if (maybeUser.isPresent()) {
            throw new ValidationException("User already exist: " + maybeUser.get().getFirstName());
        }
    }
}
