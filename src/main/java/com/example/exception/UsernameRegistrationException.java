package com.example.exception;

public class UsernameRegistrationException extends RuntimeException{
    public UsernameRegistrationException(String message) {
        super(message);
    }
}
