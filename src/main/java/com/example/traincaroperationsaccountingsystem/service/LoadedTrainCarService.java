package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarDTO;
import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarRequestDTO;

import java.util.List;

public interface LoadedTrainCarService {

    LoadedTrainCarDTO findLoadedTrainCarById(int id);

    List<LoadedTrainCarDTO> findAllLoadedTrainCars();

    List<LoadedTrainCarDTO> addLoadedTrainCars(List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS, int railwayId);

    List<LoadedTrainCarDTO> moveLoadedTrainCars(List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS, int targetRailwayId);

    void deleteLoadedTrainCars(List<Integer> loadedTrainCarIds);
}
