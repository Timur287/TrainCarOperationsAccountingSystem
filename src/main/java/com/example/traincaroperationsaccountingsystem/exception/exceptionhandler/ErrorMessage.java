package com.example.traincaroperationsaccountingsystem.exception.exceptionhandler;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    private String message;

}
