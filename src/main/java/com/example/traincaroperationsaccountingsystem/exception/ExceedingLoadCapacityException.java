package com.example.traincaroperationsaccountingsystem.exception;

public class ExceedingLoadCapacityException extends RuntimeException{

    public ExceedingLoadCapacityException(String message) {
        super(message);
    }
}
