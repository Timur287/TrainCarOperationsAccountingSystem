package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.RailwayStationDTO;

import java.util.List;

public interface RailwayStationService {

    RailwayStationDTO findRailwayStationById(int id);

    RailwayStationDTO addRailwayStation(RailwayStationDTO railwayStationDTO);

    RailwayStationDTO updateRailwayStation(RailwayStationDTO railwayStationDTO);

    void deleteRailwayStationById(int id);

    List<RailwayStationDTO> findAllRailwayStations();
}
