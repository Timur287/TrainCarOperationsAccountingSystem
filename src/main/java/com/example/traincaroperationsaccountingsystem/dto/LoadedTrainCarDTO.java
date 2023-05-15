package com.example.traincaroperationsaccountingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoadedTrainCarDTO {

    private int id;

    private int serialNumber;

    @JsonProperty(value = "railway")
    private RailwayDTO railwayDTO;

    @JsonProperty(value = "trainCar")
    private TrainCarDTO trainCarDTO;

    @JsonProperty(value = "cargo")
    private CargoDTO cargoDTO;

    private int cargoWeight;

    private int trainCarWeight;
}
