package com.example.traincaroperationsaccountingsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CargoDTO {
    private int id;
    private String name;

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
