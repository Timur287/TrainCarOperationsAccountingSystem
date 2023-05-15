package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.CargoDTO;
import com.example.traincaroperationsaccountingsystem.exception.CargoNotFoundException;
import com.example.traincaroperationsaccountingsystem.mappers.CargoDTOModelMapper;
import com.example.traincaroperationsaccountingsystem.model.Cargo;
import com.example.traincaroperationsaccountingsystem.repository.CargoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class CargoServiceImpl implements CargoService{
    private CargoRepository cargoRepository;
    @Override
    @Transactional
    public CargoDTO findCargoById(int id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new CargoNotFoundException("Cargo with id " + id + " not found"));

        return CargoDTOModelMapper.toDTO(cargo);
    }
    @Override
    @Transactional
    public CargoDTO addCargo(CargoDTO cargoDTO) {

        Cargo cargo = CargoDTOModelMapper.toModel(cargoDTO);
        cargoRepository.save(cargo);

        log.info("Cargo with id {} has been added", cargo.getId());

        return CargoDTOModelMapper.toDTO(cargo);

    }
    @Override
    @Transactional
    public CargoDTO updateCargo(CargoDTO cargoDTO) {

        Cargo oldCargo = cargoRepository.findById(cargoDTO.getId())
                .orElseThrow(() -> new CargoNotFoundException("Cargo with id " + cargoDTO.getId() + " not found"));

        oldCargo.setName(cargoDTO.getName());

        log.info("Cargo with id {} has been updated", oldCargo.getId());
        return CargoDTOModelMapper.toDTO(oldCargo);
    }
    @Override
    @Transactional
    public void deleteCargoById(int id) {

        log.info("Cargo with id {} has been deleted", id);

        cargoRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<CargoDTO> findAllCargos() {

        List<Cargo> cargos = cargoRepository.findAll();

        List<CargoDTO> cargoDTOS = new ArrayList<>();
        for(Cargo cargo: cargos){
            cargoDTOS.add(CargoDTOModelMapper.toDTO(cargo));
        }
        return cargoDTOS;
    }
}
