package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.TrainCarDTO;
import com.example.traincaroperationsaccountingsystem.model.TrainCar;

public class TrainCarDTOModelMapper {

    public static TrainCarDTO toDTO(TrainCar trainCar){
        return TrainCarDTO.builder()
                .id(trainCar.getId())
                .type(trainCar.getType())
                .loadCapacity(trainCar.getLoadCapacity())
                .tareWeight(trainCar.getTareWeight())
                .build();
    }

    public static TrainCar toModel(TrainCarDTO trainCarDTO){
        return TrainCar.builder()
                .id(trainCarDTO.getId())
                .type(trainCarDTO.getType())
                .loadCapacity(trainCarDTO.getLoadCapacity())
                .tareWeight(trainCarDTO.getTareWeight())
                .build();
    }
}
