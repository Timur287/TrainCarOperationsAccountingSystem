package com.example.traincaroperationsaccountingsystem.contoller;

import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarDTO;
import com.example.traincaroperationsaccountingsystem.dto.LoadedTrainCarRequestDTO;
import com.example.traincaroperationsaccountingsystem.service.LoadedTrainCarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loadedTrainCars")
@AllArgsConstructor
public class LoadedTrainCarController {

    private LoadedTrainCarServiceImpl loadedTrainCarServiceImpl;

    @GetMapping
    public List<LoadedTrainCarDTO> getAllLoadedTrainCars(){
        return loadedTrainCarServiceImpl.findAllLoadedTrainCars();
    }

    @GetMapping("/{id}")
    public LoadedTrainCarDTO getLoadedTrainCarById(@PathVariable int id){
        return loadedTrainCarServiceImpl.findLoadedTrainCarById(id);
    }

    @PostMapping
    public List<LoadedTrainCarDTO> addLoadedTrainCars(
            @RequestBody List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS,
            @RequestParam int railwayId
    ){
        return loadedTrainCarServiceImpl.addLoadedTrainCars(loadedTrainCarRequestDTOS, railwayId);
    }

    @PutMapping("/move")
    public List<LoadedTrainCarDTO> moveLoadedTrainCars(@RequestBody List<LoadedTrainCarRequestDTO> loadedTrainCarRequestDTOS, int targetRailwayId){

        return loadedTrainCarServiceImpl.moveLoadedTrainCars(loadedTrainCarRequestDTOS, targetRailwayId);
    }

    @DeleteMapping()
    public void deleteLoadedTrainCars(@RequestParam List<Integer> ids){

        loadedTrainCarServiceImpl.deleteLoadedTrainCars(ids);
    }
}
