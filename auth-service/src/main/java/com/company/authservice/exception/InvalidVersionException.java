package com.company.authservice.exception;

public class InvalidVersionException extends RuntimeException{

    public InvalidVersionException(String message) {
        super(message);
    }

}
