package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarDTO;
import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarRequestDTO;
import com.example.traincaroperationsaccountingsystem.model.Cargo;
import com.example.traincaroperationsaccountingsystem.model.LoadedTrainCar;
import com.example.traincaroperationsaccountingsystem.model.Railway;
import com.example.traincaroperationsaccountingsystem.model.TrainCar;

public class LoadedTrainCarRequestDTOModelMapper {

    public static LoadedTrainCarDTO toDTO (LoadedTrainCar loadedTrainCar){

        return LoadedTrainCarDTO.builder()
                .id(loadedTrainCar.getId())
                .serialNumber(loadedTrainCar.getSerialNumber())
                .railwayDTO(RailwayDTOModelMapper.toDTO(loadedTrainCar.getRailway()))
                .trainCarDTO(TrainCarDTOModelMapper.toDTO(loadedTrainCar.getTrainCar()))
                .cargoDTO(CargoDTOModelMapper.toDTO(loadedTrainCar.getCargo()))
                .cargoWeight(loadedTrainCar.getCargoWeight())
                .trainCarWeight(loadedTrainCar.getTrainCarWeight())
                .build();
    }

    public static LoadedTrainCar toModel(LoadedTrainCarRequestDTO loadedTrainCarRequestDTO, TrainCar trainCar, Cargo cargo, Railway railway) {
        return LoadedTrainCar.builder()
                .railway(railway)
                .trainCar(trainCar)
                .cargo(cargo)
                .cargoWeight(loadedTrainCarRequestDTO.getCargoWeight())
                .trainCarWeight(loadedTrainCarRequestDTO.getTrainCarWeight())
                .build();

    }
}
