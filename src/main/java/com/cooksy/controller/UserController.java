package com.cooksy.controller;

import com.cooksy.dto.UserDto;
import com.cooksy.service.DecodeTokenService;
import com.cooksy.service.UserService;
import com.cooksy.util.enums.UserSortedType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cooksy.dto.Id.idFromLong;
import static com.cooksy.dto.Id.idFromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final DecodeTokenService decodeTokenService;

//    @GetMapping(produces = APPLICATION_JSON_VALUE)
//    public List<UserDto> getAllUsers(@RequestParam(required = false) String sortBy) {
//        return (sortBy == null) ? userService.getUsers() : userService.getSortedUsers(UserSortedType.valueOf(sortBy));
//    }

    @GetMapping( produces = APPLICATION_JSON_VALUE)
    public UserDto getUser(@RequestHeader("Authorization") String headerValue) {
        return userService.getUserById(idFromLong(decodeTokenService.getUserIdFromToken(headerValue)));
    }

//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    @ResponseStatus(CREATED)
//    public void addUser(@RequestBody UserDto userDto) {
//        userService.addUser(userDto);
//    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void updateUser( @RequestBody UserDto userDto, @RequestHeader("Authorization") String headerValue) {
        userService.updateUser(idFromLong(decodeTokenService.getUserIdFromToken(headerValue)), userDto);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@RequestHeader("Authorization") String headerValue) {
        userService.removeUser(idFromLong(decodeTokenService.getUserIdFromToken(headerValue)));
    }

}
