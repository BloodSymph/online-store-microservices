package com.company.productservice.exception;

public class ProductInfoNotFoundException extends RuntimeException {

    private final Long serialVersionUID = 1L;

    public ProductInfoNotFoundException(String message) {
        super(message);
    }
}
