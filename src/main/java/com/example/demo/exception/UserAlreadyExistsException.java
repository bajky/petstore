package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User with same user name already eixists.");
    }
}
