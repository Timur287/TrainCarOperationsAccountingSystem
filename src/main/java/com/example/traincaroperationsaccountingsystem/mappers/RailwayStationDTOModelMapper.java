package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.RailwayStationDTO;
import com.example.traincaroperationsaccountingsystem.model.RailwayStation;

public class RailwayStationDTOModelMapper {

    public static RailwayStationDTO toDTO(RailwayStation railwayStation){
        return RailwayStationDTO.builder()
                .id(railwayStation.getId())
                .name(railwayStation.getName())
                .address(railwayStation.getAddress())
                .build();
    }

    public static RailwayStation toModel(RailwayStationDTO railwayStationDTO){
        return RailwayStation.builder()
                .id(railwayStationDTO.getId())
                .name(railwayStationDTO.getName())
                .address(railwayStationDTO.getAddress())
                .build();
    }
}
