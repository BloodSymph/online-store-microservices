package com.company.productservice.exception;

public class InvalidVersionException extends RuntimeException{

    public InvalidVersionException(String message) {
        super(message);
    }

}

