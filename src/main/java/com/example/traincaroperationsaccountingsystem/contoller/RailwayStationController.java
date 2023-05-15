package com.example.traincaroperationsaccountingsystem.contoller;

import com.example.traincaroperationsaccountingsystem.dto.RailwayStationDTO;
import com.example.traincaroperationsaccountingsystem.service.RailwayStationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/railwayStations")
public class RailwayStationController {

    @Autowired
    RailwayStationServiceImpl railwayStationServiceImpl;

    @GetMapping
    public List<RailwayStationDTO> getAllRailwayStations(){
        return railwayStationServiceImpl.findAllRailwayStations();
    }

    @GetMapping("/{id}")
    public RailwayStationDTO getRailwayStationById(@PathVariable int id){
        return railwayStationServiceImpl.findRailwayStationById(id);
    }

    @PostMapping
    public RailwayStationDTO addRailwayStation(@RequestBody RailwayStationDTO railwayStationDTO){
        return railwayStationServiceImpl.addRailwayStation(railwayStationDTO);
    }

    @PutMapping
    public RailwayStationDTO updateRailwayStation(@RequestBody RailwayStationDTO railwayStationDTO){
        return railwayStationServiceImpl.updateRailwayStation(railwayStationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRailwayStation(@PathVariable int id){
        railwayStationServiceImpl.deleteRailwayStationById(id);
    }
}
