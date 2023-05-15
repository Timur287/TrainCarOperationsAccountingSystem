package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.RailwayDTO;

import java.util.List;

public interface RailwayService {

    RailwayDTO findRailwayById(int id);

    RailwayDTO addRailway(RailwayDTO railwayDTO) ;

    RailwayDTO updateRailway(RailwayDTO railwayDTO);

    void deleteRailwayById(int id);

    List<RailwayDTO> findAllRailways();
}
