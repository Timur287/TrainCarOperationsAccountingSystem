package com.example.traincaroperationsaccountingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RailwayDTO {

    private int id;

    @JsonProperty(value = "railwayStation")
    private RailwayStationDTO railwayStationDTO;
}
