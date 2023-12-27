package com.company.authservice.exception;

public class UsernameIsTakenException extends RuntimeException{

    public UsernameIsTakenException(String message) {
        super(message);
    }

}
