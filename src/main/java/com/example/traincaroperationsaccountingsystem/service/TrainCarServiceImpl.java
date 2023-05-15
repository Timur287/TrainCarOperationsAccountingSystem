package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.TrainCarDTO;
import com.example.traincaroperationsaccountingsystem.exception.TrainCarNotFoundException;
import com.example.traincaroperationsaccountingsystem.mappers.TrainCarDTOModelMapper;
import com.example.traincaroperationsaccountingsystem.model.TrainCar;
import com.example.traincaroperationsaccountingsystem.repository.TrainCarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class TrainCarServiceImpl implements TrainCarService{

    private TrainCarRepository trainCarRepository;
    @Override
    @Transactional
    public TrainCarDTO findTrainCarById(int id) {
        TrainCar trainCar =  trainCarRepository.findById(id)
                .orElseThrow(()-> new TrainCarNotFoundException("Train car with id " + id + " not found"));

        return TrainCarDTOModelMapper.toDTO(trainCar);
    }
    @Override
    @Transactional
    public TrainCarDTO addTrainCar(TrainCarDTO trainCarDTO) {
        TrainCar trainCar = TrainCarDTOModelMapper.toModel(trainCarDTO);
        TrainCarDTOModelMapper.toDTO(trainCarRepository.save(trainCar));

        log.info("Train car with id {} has been added", trainCar.getId());

        return TrainCarDTOModelMapper.toDTO(trainCar);
    }
    @Override
    @Transactional
    public TrainCarDTO updateTrainCar(TrainCarDTO trainCarDTO) {

        TrainCar oldTrainCar = trainCarRepository.findById(trainCarDTO.getId())
                .orElseThrow(() -> new TrainCarNotFoundException(
                        "Train car with id " + trainCarDTO.getId() + " not found"
                ));
        oldTrainCar.setType(trainCarDTO.getType());
        oldTrainCar.setLoadCapacity(trainCarDTO.getLoadCapacity());
        oldTrainCar.setTareWeight(trainCarDTO.getTareWeight());

        log.info("Train car with id {} has been updated", oldTrainCar.getId());

        return TrainCarDTOModelMapper.toDTO(oldTrainCar);
    }
    @Override
    @Transactional
    public void deleteTrainCarById(int id) {

        log.info("Train car with id {} has been deleted", id);
        trainCarRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<TrainCarDTO> findAllTrainCars() {
        List<TrainCar> trainCars = trainCarRepository.findAll();
        List<TrainCarDTO> trainCarDTOS = new ArrayList<>(trainCars.size());
        for(TrainCar trainCar: trainCars){
            trainCarDTOS.add(TrainCarDTOModelMapper.toDTO(trainCar));
        }
        return trainCarDTOS;
    }
}
