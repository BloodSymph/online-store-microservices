package com.company.productservice.exception;

public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException(String message) {
        super(message);
    }

}
