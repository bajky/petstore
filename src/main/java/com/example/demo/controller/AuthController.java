package com.example.demo.controller;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public void handleRegisterUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }

    @GetMapping(path = "/login")
    @ResponseBody
    public UserDto handleLogin() {
        UserDto userDto = new UserDto();

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();

        userDto.setUserName(username);
        return userDto;
    }
}
