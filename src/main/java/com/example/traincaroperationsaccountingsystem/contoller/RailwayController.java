package com.example.traincaroperationsaccountingsystem.contoller;

import com.example.traincaroperationsaccountingsystem.dto.RailwayDTO;
import com.example.traincaroperationsaccountingsystem.service.RailwayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/railways")
@AllArgsConstructor
public class RailwayController {

    private RailwayServiceImpl railwayServiceImpl;

    @GetMapping
    public List<RailwayDTO> getAllRailways(){
        return railwayServiceImpl.findAllRailways();
    }

    @GetMapping("/{id}")
    public RailwayDTO getRailwayById(@PathVariable int id){
        return railwayServiceImpl.findRailwayById(id);
    }

    @PostMapping
    public RailwayDTO addRailway(@RequestBody RailwayDTO railwayDTO){
        return railwayServiceImpl.addRailway(railwayDTO);
    }

    @PutMapping
    public RailwayDTO updateRailway(@RequestBody RailwayDTO railwayDTO){
        return railwayServiceImpl.updateRailway(railwayDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRailwayById(@PathVariable int id){
        railwayServiceImpl.deleteRailwayById(id);
    }



}
