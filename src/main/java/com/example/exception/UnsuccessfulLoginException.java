package com.example.exception;

public class UnsuccessfulLoginException extends RuntimeException{
    public UnsuccessfulLoginException(String message) {
        super(message);
    }
}
