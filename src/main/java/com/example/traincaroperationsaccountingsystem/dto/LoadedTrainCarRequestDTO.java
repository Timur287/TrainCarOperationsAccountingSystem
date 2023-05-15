package com.example.traincaroperationsaccountingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class LoadedTrainCarRequestDTO {

    private int id;

    @JsonProperty(value = "trainCar")
    private TrainCarDTO trainCarDTO;

    @JsonProperty(value = "cargo")
    private CargoDTO cargoDTO;

    private int cargoWeight;

    private int trainCarWeight;
}
