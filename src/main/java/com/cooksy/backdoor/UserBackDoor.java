package com.cooksy.backdoor;

import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import com.cooksy.util.type.UserSortedType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cooksy.dto.Id.idFromString;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserBackDoor {

    private final UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(@RequestParam(required = false) String sortBy) {
        if (sortBy == null) {
            userService.getUsers();
        }
        else if (UserSortedType.isContains(sortBy)) {
            return userService.getSortedUsers(UserSortedType.valueOf(sortBy));
        }
        return userService.getUsersByTypeId(idFromString(sortBy));
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(idFromString(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        userService.updateUser(idFromString(id), userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(idFromString(id));
    }
}
