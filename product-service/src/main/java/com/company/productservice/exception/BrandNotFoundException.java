package com.company.productservice.exception;

public class BrandNotFoundException extends RuntimeException{
    private final Long serialVersionUID = 1L;

    public BrandNotFoundException(String message) {
        super(message);
    }
}
