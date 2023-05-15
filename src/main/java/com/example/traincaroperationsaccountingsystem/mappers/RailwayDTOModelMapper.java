package com.example.traincaroperationsaccountingsystem.mappers;

import com.example.traincaroperationsaccountingsystem.dto.RailwayDTO;
import com.example.traincaroperationsaccountingsystem.model.Railway;

public class RailwayDTOModelMapper {

    public static RailwayDTO toDTO(Railway railway){
        return RailwayDTO.builder()
                .id(railway.getId())
                .railwayStationDTO(
                        RailwayStationDTOModelMapper.toDTO(railway.getRailwayStation())
                ).build();
    }
}
