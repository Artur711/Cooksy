package com.cooksy.controller;

import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import com.cooksy.util.enums.UserSortedType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cooksy.dto.Id.idFromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

//    @GetMapping(produces = APPLICATION_JSON_VALUE)
//    public List<UserDto> getAllUsers(@RequestParam(required = false) String sortBy) {
//        return (sortBy == null) ? userService.getUsers() : userService.getSortedUsers(UserSortedType.valueOf(sortBy));
//    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(idFromString(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        userService.updateUser(idFromString(id), userDto);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(idFromString(id));
    }

}
