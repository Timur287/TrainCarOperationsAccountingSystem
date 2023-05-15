package com.example.traincaroperationsaccountingsystem.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainCarDTO {

    private int id;
    private String type;
    private int loadCapacity;
    private int tareWeight;
}
