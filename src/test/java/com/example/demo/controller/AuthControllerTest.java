package com.example.demo.controller;

import com.example.demo.controller.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DirtiesContext
    public void registration_userCorrectlySaved() throws Exception{
        UserDto user = createUser();
        mockMvc.perform(post("/auth/register")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk());

        User storedUser = this.usersRepository.findByUserName(user.getUserName());
        assertNotNull(storedUser);
    }

    @Test
    @DirtiesContext
    public void registration_userAlreadyExists() throws Exception{
        UserDto user = createUser();
        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void registration_userNameMustNotBeNull() throws Exception{
        UserDto user = createUser();
        user.setUserName(null);
        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is4xxClientError());
    }

    private UserDto createUser() {
        UserDto user = new UserDto();
        user.setUserName("userName");
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setEmail("valid@email.com");
        user.setPassword("password");

        return user;
    }
}