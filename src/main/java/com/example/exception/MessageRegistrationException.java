package com.example.exception;

public class MessageRegistrationException extends RuntimeException{
    public MessageRegistrationException(String message) {
        super(message);
    }
}
