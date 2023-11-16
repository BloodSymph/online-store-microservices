package com.company.productservice.exception;

public class ProductInfoNotFoundException extends RuntimeException {

    public ProductInfoNotFoundException(String message) {
        super(message);
    }

}

