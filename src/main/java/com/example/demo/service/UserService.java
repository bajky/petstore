package com.example.demo.service;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void registerUser(UserDto userDto);
    User getUserByName(String userName);
    boolean hasUserId(UserDetails user, String id);
}