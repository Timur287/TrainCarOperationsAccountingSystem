package com.example.traincaroperationsaccountingsystem.contoller;

import com.example.traincaroperationsaccountingsystem.dto.CargoDTO;
import com.example.traincaroperationsaccountingsystem.service.CargoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargos")
@AllArgsConstructor
public class CargoController {

    private CargoServiceImpl cargoServiceImpl;

    @GetMapping
    public List<CargoDTO> getAll(){
        return cargoServiceImpl.findAllCargos();
    }

    @GetMapping("/{id}")
    public CargoDTO getCargoById(@PathVariable int id){
        return cargoServiceImpl.findCargoById(id);
    }

    @PostMapping
    public CargoDTO addCargo(@RequestBody CargoDTO cargoDTO){
        return cargoServiceImpl.addCargo(cargoDTO);
    }

    @PutMapping
    public CargoDTO updateCargo(@RequestBody CargoDTO cargoDTO){
        return cargoServiceImpl.updateCargo(cargoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCargo(@PathVariable int id){
        cargoServiceImpl.deleteCargoById(id);
    }
}
