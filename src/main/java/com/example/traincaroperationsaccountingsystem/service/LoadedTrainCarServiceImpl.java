package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarDTO;
import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarRequestDTO;
import com.example.traincaroperationsaccountingsystem.exception.*;
import com.example.traincaroperationsaccountingsystem.mappers.*;
import com.example.traincaroperationsaccountingsystem.model.Cargo;
import com.example.traincaroperationsaccountingsystem.model.LoadedTrainCar;
import com.example.traincaroperationsaccountingsystem.model.Railway;
import com.example.traincaroperationsaccountingsystem.model.TrainCar;
import com.example.traincaroperationsaccountingsystem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoadedTrainCarServiceImpl implements LoadedTrainCarService {

    private final LoadedTrainCarRepository loadedTrainCarRepository;
    private final TrainCarRepository trainCarRepository;
    private final CargoRepository cargoRepository;
    private final RailwayRepository railwayRepository;

    @Override
    @Transactional
    public LoadedTrainCarDTO findLoadedTrainCarById(int id) {
        LoadedTrainCar loadedTrainCar = loadedTrainCarRepository.findById(id)
                .orElseThrow(() -> new LoadedTrainCarNotFoundException("Loaded train car with id " + id + " not found"));
        loadedTrainCar.setSerialNumber(loadedTrainCar.getRailway().getTrain().indexOf(loadedTrainCar));
        return LoadedTrainCarDTOModelMapper.toDTO(loadedTrainCar);
    }

    @Override
    @Transactional
    public List<LoadedTrainCarDTO> findAllLoadedTrainCars() {

        List<LoadedTrainCar> loadedTrainCars = loadedTrainCarRepository.findAll();

        List<LoadedTrainCarDTO> loadedTrainCarDTOS = new ArrayList<>(loadedTrainCars.size());

        for (LoadedTrainCar loadedTrainCar : loadedTrainCars) {
            loadedTrainCar.setSerialNumber(loadedTrainCar.getRailway().getTrain().indexOf(loadedTrainCar));
            loadedTrainCarDTOS.add(LoadedTrainCarDTOModelMapper.toDTO(loadedTrainCar));
        }

        return loadedTrainCarDTOS;
    }
    @Override
    @Transactional
    public List<LoadedTrainCarDTO> addLoadedTrainCars(List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS, int railwayId) {

        List<LoadedTrainCar> loadedTrainCars = new ArrayList<>(loadedTrainCarRequestDTOS.size());

        Railway railway = railwayRepository.findById(railwayId)
                .orElseThrow(() -> new RailwayNotFoundException("Railway with id " + railwayId + " not found"));

        for (LoadedTrainCarRequestDTO loadedTrainCarRequestDTO : loadedTrainCarRequestDTOS) {

            TrainCar trainCar = trainCarRepository.findById(loadedTrainCarRequestDTO.getTrainCarDTO().getId())
                    .orElseThrow(() -> new TrainCarNotFoundException("Train car with id " + loadedTrainCarRequestDTO.getTrainCarDTO().getId() + " not found"));

            Cargo cargo = cargoRepository.findById(loadedTrainCarRequestDTO.getCargoDTO().getId())
                    .orElseThrow(() -> new CargoNotFoundException("Cargo with id " + loadedTrainCarRequestDTO.getCargoDTO().getId() + " not found"));


            if (trainCar.getLoadedTrainCar() != null) {
                throw new TrainCarIsAlreadyInUseException("Train car with id " + trainCar.getId() + " is already in use");
            }

            if (cargo.getLoadedTrainCar() != null) {
                throw new CargoIsAlreadyInUseException("Cargo with id " + cargo.getId() + " is already in use");
            }

            LoadedTrainCar loadedTrainCar = LoadedTrainCarRequestDTOModelMapper.toModel(loadedTrainCarRequestDTO, trainCar, cargo, railway);

            if (loadedTrainCar.getCargoWeight() > trainCar.getLoadCapacity()) {
                throw new ExceedingLoadCapacityException("The weight of the cargo is more than the maximum load capacity of the train car");
            }

            loadedTrainCars.add(loadedTrainCar);
        }

        railway.addLoadedTrainCars(loadedTrainCars);

        loadedTrainCarRepository.saveAll(loadedTrainCars);

        updateSerialNumbers(railway.getTrain());

        log.info("New train cars were added to the railway with id {} ", railwayId);

        return LoadedTrainCarDTOModelMapper.toDTOs(loadedTrainCars);
    }
    @Override
    @Transactional
    public List<LoadedTrainCarDTO> moveLoadedTrainCars(List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS, int targetRailwayId) {

        Railway targetRailway = railwayRepository.findById(targetRailwayId)
                .orElseThrow(() -> new RailwayNotFoundException("Target railway with id " + targetRailwayId + " not found"));

        int firstLoadedTrainCarToMoveId = loadedTrainCarRequestDTOS.get(0).getId();
        LoadedTrainCar firstLoadedTrainCarToMove = loadedTrainCarRepository.findById(firstLoadedTrainCarToMoveId).orElseThrow(() -> new LoadedTrainCarNotFoundException(
                "Loaded train car with id " + firstLoadedTrainCarToMoveId + " not found"));

        checkLoadedTrainCarRequestDTODependencies(firstLoadedTrainCarToMove, loadedTrainCarRequestDTOS.get(0));

        Railway sourceRailway = railwayRepository.findById(firstLoadedTrainCarToMove.getRailway().getId())
                .orElseThrow(() -> new RailwayNotFoundException("Railway with id " + firstLoadedTrainCarToMove.getRailway().getId() + " not found"));

        if (firstLoadedTrainCarToMove.getSerialNumber() != 0 ||
                firstLoadedTrainCarToMove.getSerialNumber() != firstLoadedTrainCarToMove.getRailway().getTrain().size() - 1) {
            throw new IncorrectTrainCarLocationException("Train cars can only be moved from the beginning or end of the train");
        }

        int serialNumberCounter = firstLoadedTrainCarToMove.getSerialNumber();

        boolean isFirstLoadedTrainCar = serialNumberCounter == 0;

        List<LoadedTrainCar> loadedTrainCars = new ArrayList<>(loadedTrainCarRequestDTOS.size());
        loadedTrainCars.add(firstLoadedTrainCarToMove);

        for (int counter = 1; counter < loadedTrainCarRequestDTOS.size(); counter++) {

            if (isFirstLoadedTrainCar) {
                serialNumberCounter++;
            } else {
                serialNumberCounter--;
            }

            int loadedTrainCarRequestDTOId = loadedTrainCarRequestDTOS.get(counter).getId();

            LoadedTrainCar loadedTrainCar = loadedTrainCarRepository.findById(loadedTrainCarRequestDTOId)
                    .orElseThrow(() -> new LoadedTrainCarNotFoundException(
                            "Loaded train car with id " + loadedTrainCarRequestDTOId + " not found")
                    );

            checkLoadedTrainCarRequestDTODependencies(loadedTrainCar, loadedTrainCarRequestDTOS.get(counter));

            if (loadedTrainCar.getSerialNumber() != serialNumberCounter) {
                throw new IncorrectTrainCarLocationException("Train cars can only be moved from the beginning or end of the train");
            }

            if (loadedTrainCar.getRailway().getId() != sourceRailway.getId()) {
                throw new IncorrectTrainCarLocationException("Train cars must stand on the same railway");
            }

            loadedTrainCars.add(loadedTrainCar);
        }


        sourceRailway.removeLoadedTrainCars(loadedTrainCars);
        targetRailway.addLoadedTrainCars(loadedTrainCars);

        updateSerialNumbers(sourceRailway.getTrain());
        updateSerialNumbers(targetRailway.getTrain());

        log.info("Train cars were moved from the railway with id {} to the railway with id {} ", sourceRailway.getId(), targetRailwayId);

        return LoadedTrainCarDTOModelMapper.toDTOs(loadedTrainCars);
    }
    @Override
    @Transactional
    public void deleteLoadedTrainCars(List<Integer> loadedTrainCarIds) {

        List<LoadedTrainCar> loadedTrainCars = new ArrayList<>(loadedTrainCarIds.size());

        int firstLoadedTrainCarId = loadedTrainCarIds.get(0);

        LoadedTrainCar firstLoadedTrainCar = loadedTrainCarRepository.findById(firstLoadedTrainCarId).orElseThrow(() -> new LoadedTrainCarNotFoundException(
                "Loaded train car with id " + firstLoadedTrainCarId + " not found")
        );

        Railway firstLoadedTrainCarRailway = railwayRepository.findById(firstLoadedTrainCar.getRailway().getId())
                .orElseThrow(() -> new RailwayNotFoundException("Railway with id " + firstLoadedTrainCar.getRailway().getId() + " not found"));

        if (firstLoadedTrainCar.getSerialNumber() != 0) {
            throw new IncorrectTrainCarLocationException("The operation of the departure of train cars can only be carried out with train cars from the beginning of the train");
        }

        loadedTrainCars.add(firstLoadedTrainCar);

        int serialNumberCounter = firstLoadedTrainCar.getSerialNumber();

        for (int counter = 1; counter < loadedTrainCarIds.size(); counter++) {

            serialNumberCounter++;

            int loadedTrainCarId = loadedTrainCarIds.get(counter);

            LoadedTrainCar loadedTrainCar = loadedTrainCarRepository.findById(loadedTrainCarId)
                    .orElseThrow(() -> new LoadedTrainCarNotFoundException(
                            "Loaded train car with id " + loadedTrainCarId + " not found")
                    );

            if (loadedTrainCar.getSerialNumber() != serialNumberCounter) {
                throw new IncorrectTrainCarLocationException("Train cars can only be removed from the beginning of the train");
            }

            if (loadedTrainCar.getRailway().getId() != firstLoadedTrainCarRailway.getId()) {
                throw new IncorrectTrainCarLocationException("Train cars must stand on the same railway");

            }

            loadedTrainCars.add(loadedTrainCar);
        }

        firstLoadedTrainCarRailway.removeLoadedTrainCars(loadedTrainCars);

        log.info("{} train car left railway with id {} ", loadedTrainCars.size(), firstLoadedTrainCarRailway.getId());

        loadedTrainCarRepository.deleteAll(loadedTrainCars);

        updateSerialNumbers(firstLoadedTrainCarRailway.getTrain());
    }

    private void checkLoadedTrainCarRequestDTODependencies(LoadedTrainCar loadedTrainCar, LoadedTrainCarRequestDTO loadedTrainCarRequestDTO) {
        checkTrainCar(loadedTrainCar, loadedTrainCarRequestDTO);
        checkCargo(loadedTrainCar, loadedTrainCarRequestDTO);
    }

    private void checkTrainCar(LoadedTrainCar loadedTrainCar, LoadedTrainCarRequestDTO loadedTrainCarRequestDTO) {
        if (loadedTrainCar.getTrainCar().getId() != loadedTrainCarRequestDTO.getTrainCarDTO().getId()) {
            throw new TrainCarNotFoundException("Train car id in loaded train car with id " + loadedTrainCarRequestDTO.getTrainCarDTO().getId() + " is incorrectly specified");
        }
    }

    private void checkCargo(LoadedTrainCar loadedTrainCar, LoadedTrainCarRequestDTO loadedTrainCarRequestDTO) {
        if (loadedTrainCar.getCargo().getId() != loadedTrainCarRequestDTO.getCargoDTO().getId()) {
            throw new CargoNotFoundException("Cargo id in loaded train car with id " + loadedTrainCarRequestDTO.getCargoDTO().getId() + "is incorrectly specified");
        }
    }

    private void updateSerialNumbers(List<LoadedTrainCar> train) {
        for(LoadedTrainCar loadedTrainCar : train){
            loadedTrainCar.setSerialNumber(train.indexOf(loadedTrainCar));

            log.info("Serial number of train car with id {} has been updated to {}", loadedTrainCar.getId(), loadedTrainCar.getSerialNumber());
        }
    }
}
