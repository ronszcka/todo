package com.utfpr.todo.exceptions;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(String message) {
        super(message);
    }

}