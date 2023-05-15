package com.example.traincaroperationsaccountingsystem.exception;

public class TrainCarIsAlreadyInUseException extends RuntimeException{

    public TrainCarIsAlreadyInUseException(String message) {
        super(message);
    }
}
