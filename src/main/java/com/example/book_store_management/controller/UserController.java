package com.example.book_store_management.controller;

import com.example.book_store_management.dto.UserDto;
import com.example.book_store_management.dto.UserUpdateDto;
import com.example.book_store_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUsersById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUsers(@PathVariable("id") int id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    @DeleteMapping("/delete")
    public void deleteUsers(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
