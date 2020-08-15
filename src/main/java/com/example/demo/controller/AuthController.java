package com.example.demo.controller;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public AuthController(UserService userService, RepositoryEntityLinks entityLinks) {
        this.userService = userService;
        this.entityLinks = entityLinks;
    }

    @PostMapping(path = "/register")
    public void handleRegisterUser(@Valid @RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<UserDto> handleLogin() {
        UserDto userDto = new UserDto();

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();
        User user = userService.getUserByName(username);

        userDto.add(entityLinks.linkToItemResource(UsersRepository.class, user.getId()));
        userDto.add(entityLinks.linkToItemResource(UsersRepository.class, user.getId()).withSelfRel());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return ResponseEntity.ok(userDto);
    }
}
