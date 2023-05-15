package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.CargoDTO;

import java.util.List;

public interface CargoService {

    CargoDTO findCargoById(int id);

    CargoDTO addCargo(CargoDTO cargoDTO);

    CargoDTO updateCargo(CargoDTO cargoDTO);

    void deleteCargoById(int id);

    List<CargoDTO> findAllCargos();

}
