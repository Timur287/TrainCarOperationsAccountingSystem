package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarDTO;
import com.example.traincaroperationsaccountingsystem.model.Cargo;
import com.example.traincaroperationsaccountingsystem.model.LoadedTrainCar;
import com.example.traincaroperationsaccountingsystem.model.Railway;
import com.example.traincaroperationsaccountingsystem.model.TrainCar;

import java.util.ArrayList;
import java.util.List;

public class LoadedTrainCarDTOModelMapper {


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

    public static List<LoadedTrainCarDTO> toDTOs(List<LoadedTrainCar> loadedTrainCars){
        List<LoadedTrainCarDTO> loadedTrainCarDTOS = new ArrayList<>(loadedTrainCars.size());

        for (LoadedTrainCar loadedTrainCar : loadedTrainCars) {
            loadedTrainCarDTOS.add(LoadedTrainCarDTOModelMapper.toDTO(loadedTrainCar));
        }

        return loadedTrainCarDTOS;
    }

    public static LoadedTrainCar toModel(LoadedTrainCarDTO loadedTrainCarDTO, TrainCar trainCar, Cargo cargo, Railway railway) {
        return LoadedTrainCar.builder()
                .railway(railway)
                .trainCar(trainCar)
                .cargo(cargo)
                .cargoWeight(loadedTrainCarDTO.getCargoWeight())
                .trainCarWeight(loadedTrainCarDTO.getTrainCarWeight())
                .build();
    }
}
