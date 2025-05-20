package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAndErrorController {
    
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateUserName(DuplicateUsernameException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsernameReg(UsernameRegistrationException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UnsuccessfulLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleLoginFailure(UnsuccessfulLoginException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MessageRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMessageCreateFailure(MessageRegistrationException ex){
        return ex.getMessage();
    }

}
