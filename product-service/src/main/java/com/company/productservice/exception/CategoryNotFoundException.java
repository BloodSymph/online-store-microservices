package com.company.productservice.exception;

public class CategoryNotFoundException extends RuntimeException{

    private final Long serialVersionUID = 1L;

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
