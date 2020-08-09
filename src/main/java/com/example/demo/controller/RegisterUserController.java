package com.example.demo.controller;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class RegisterUserController {

    private final UserService userService;

    @Autowired
    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public void handleRegisterUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }
}
