package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.RailwayStationDTO;
import com.example.traincaroperationsaccountingsystem.exception.RailwayStationNotFoundException;
import com.example.traincaroperationsaccountingsystem.mappers.RailwayStationDTOModelMapper;
import com.example.traincaroperationsaccountingsystem.model.RailwayStation;
import com.example.traincaroperationsaccountingsystem.repository.RailwayStationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RailwayStationServiceImpl implements RailwayStationService{

    private RailwayStationRepository railwayStationRepository;

    @Override
    @Transactional
    public RailwayStationDTO findRailwayStationById(int id) {
        RailwayStation railwayStation = railwayStationRepository.findById(id)
                .orElseThrow(() -> new RailwayStationNotFoundException("RailwayStation with id " + id + " not found"));
        return RailwayStationDTOModelMapper.toDTO(railwayStation);
    }

    @Override
    @Transactional
    public RailwayStationDTO addRailwayStation(RailwayStationDTO railwayStationDTO) {

        RailwayStation railwayStation = RailwayStationDTOModelMapper.toModel(railwayStationDTO);
        railwayStationRepository.save(railwayStation);

        log.info("Railway station with id {} has been added", railwayStation.getId());

        return RailwayStationDTOModelMapper.toDTO(railwayStation);
    }
    @Override
    @Transactional
    public RailwayStationDTO updateRailwayStation(RailwayStationDTO railwayStationDTO) {
        RailwayStation oldRailwayStation = railwayStationRepository.findById(railwayStationDTO.getId())
                .orElseThrow(() -> new RailwayStationNotFoundException(
                        "RailwayStation with id " + railwayStationDTO.getId() + " not found"
                ));
        oldRailwayStation.setName(railwayStationDTO.getName());
        oldRailwayStation.setAddress(railwayStationDTO.getAddress());

        log.info("Railway station with id {} has been updated", oldRailwayStation.getId());


        return RailwayStationDTOModelMapper.toDTO(oldRailwayStation);
    }
    @Override
    @Transactional
    public void deleteRailwayStationById(int id) {

        log.info("Railway station with id {} has been deleted", id);

        railwayStationRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<RailwayStationDTO> findAllRailwayStations() {

        List<RailwayStation> railwayStations = railwayStationRepository.findAll();
        List<RailwayStationDTO> railwayStationDTOS = new ArrayList<>(railwayStations.size());

        for(RailwayStation railwayStation: railwayStations){
            railwayStationDTOS.add(RailwayStationDTOModelMapper.toDTO(railwayStation));
        }

        return railwayStationDTOS;
    }
}
