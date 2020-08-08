package com.example.demo.controller;

import com.example.demo.model.TestModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class TestController {

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public TestModel getUserTest(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        TestModel user = new TestModel();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
