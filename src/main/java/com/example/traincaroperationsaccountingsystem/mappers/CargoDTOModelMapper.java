package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.CargoDTO;
import com.example.traincaroperationsaccountingsystem.model.Cargo;

public class CargoDTOModelMapper {

    public static CargoDTO toDTO(Cargo cargo){
        return CargoDTO.builder()
                .id(cargo.getId())
                .name(cargo.getName())
                .build();
    }

    public static Cargo toModel(CargoDTO cargoDTO){
        return Cargo.builder()
                .id(cargoDTO.getId())
                .name(cargoDTO.getName())
                .build();
    }
}
