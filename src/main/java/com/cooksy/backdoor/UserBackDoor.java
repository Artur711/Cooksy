package com.cooksy.backdoor;

import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import com.cooksy.util.type.UserSortedType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserBackDoor {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(required = false) String sortBy) {
        return sortBy != null ? userService.getSortedUsers(UserSortedType.valueOf(sortBy)) :
                userService.getUsers();
    }

    @GetMapping("?typeId=.....")
    public List<UserDto> getUsersByType(@PathVariable String typeId) {
        return userService.getUsersByTypeId(Long.valueOf(typeId));
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(Long.valueOf(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        userService.updateUser(Long.valueOf(id), userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(Long.valueOf(id));
    }
}
