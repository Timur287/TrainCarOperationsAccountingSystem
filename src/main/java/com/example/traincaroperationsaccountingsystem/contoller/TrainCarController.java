package com.example.traincaroperationsaccountingsystem.contoller;

import com.example.traincaroperationsaccountingsystem.dto.TrainCarDTO;
import com.example.traincaroperationsaccountingsystem.service.TrainCarServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainCars")
public class TrainCarController {

    private final TrainCarServiceImpl trainCarServiceImpl;

    public TrainCarController(TrainCarServiceImpl trainCarServiceImpl) {
        this.trainCarServiceImpl = trainCarServiceImpl;
    }

    @GetMapping
    public List<TrainCarDTO> getAll(){
        return trainCarServiceImpl.findAllTrainCars();
    }

    @GetMapping("/{id}")
    public TrainCarDTO getTrainCarById(@PathVariable int id){
        return trainCarServiceImpl.findTrainCarById(id);
    }

    @PostMapping
    public TrainCarDTO addTrainCar(@RequestBody TrainCarDTO trainCarDTO){
        return trainCarServiceImpl.addTrainCar(trainCarDTO);
    }

    @PutMapping
    public TrainCarDTO updateTrainCar(@RequestBody TrainCarDTO trainCarDTO){
        return trainCarServiceImpl.updateTrainCar(trainCarDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainCar(@PathVariable int id){
        trainCarServiceImpl.deleteTrainCarById(id);
    }
}
