package com.example.traincaroperationsaccountingsystem.exception;

import java.util.NoSuchElementException;

public class CargoNotFoundException extends NoSuchElementException {

    public CargoNotFoundException(String message) {
        super(message);
    }
}
