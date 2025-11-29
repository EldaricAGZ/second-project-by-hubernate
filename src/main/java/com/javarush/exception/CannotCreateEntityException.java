package com.javarush.exception;

public class CannotCreateEntityException extends RuntimeException {
    public CannotCreateEntityException(String message) {
        super(message);
    }
}
