package com.example.traincaroperationsaccountingsystem.exception;

public class CargoIsAlreadyInUseException extends RuntimeException {

    public CargoIsAlreadyInUseException(String message) {
        super(message);
    }
}
