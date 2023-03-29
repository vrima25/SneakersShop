package com.enigma.ecommerce.Exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException() {
        super("DATA ALREADY EXIST");
    }

    public DuplicateException(String message) {
        super(message);
    }
}
