package com.example.traincaroperationsaccountingsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RailwayStationDTO {

    private int id;
    private String name;
    private String address;
}
