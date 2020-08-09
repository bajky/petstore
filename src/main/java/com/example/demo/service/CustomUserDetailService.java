package com.example.demo.service;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService, UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),
                userOptional.get().getPassword(),
                AuthorityUtils.createAuthorityList("USER"));
    }

    @Override
    public void registerUser(UserDto userDto) {
        Optional<User> userByEmail = usersRepository.findByEmail(userDto.getEmail());
        userByEmail.ifPresent(userFound -> {
            throw new UserAlreadyExistsException();
        });

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        usersRepository.save(user);
    }
}
