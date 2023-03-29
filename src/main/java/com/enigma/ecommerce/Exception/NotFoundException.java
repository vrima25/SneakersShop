package com.enigma.ecommerce.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("DATA NOT FOUND");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
