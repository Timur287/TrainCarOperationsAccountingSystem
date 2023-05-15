package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.TrainCarDTO;

import java.util.List;

public interface TrainCarService {

     TrainCarDTO findTrainCarById(int id);

     TrainCarDTO addTrainCar(TrainCarDTO trainCarDTO) ;

     TrainCarDTO updateTrainCar(TrainCarDTO trainCarDTO) ;

     void deleteTrainCarById(int id) ;

     List<TrainCarDTO> findAllTrainCars();
}
