package com.company.productservice.exception;

public class ProductNotFoundException extends RuntimeException{

    private final Long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
