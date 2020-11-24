package com.cooksy.backdoor;

import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class OrderUser {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }
}
