package com.example.traincaroperationsaccountingsystem.service;

import com.example.traincaroperationsaccountingsystem.dto.RailwayDTO;
import com.example.traincaroperationsaccountingsystem.exception.RailwayNotFoundException;
import com.example.traincaroperationsaccountingsystem.exception.RailwayStationNotFoundException;
import com.example.traincaroperationsaccountingsystem.mappers.RailwayDTOModelMapper;
import com.example.traincaroperationsaccountingsystem.model.Railway;
import com.example.traincaroperationsaccountingsystem.model.RailwayStation;
import com.example.traincaroperationsaccountingsystem.repository.RailwayRepository;
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
public class RailwayServiceImpl implements RailwayService {

    private RailwayRepository railwayRepository;
    private RailwayStationRepository railwayStationRepository;

    @Transactional
    public RailwayDTO findRailwayById(int id) {
        Railway railway = railwayRepository.findById(id)
                .orElseThrow(() -> new RailwayNotFoundException("Railway with id " + id + " not found"));
        return RailwayDTOModelMapper.toDTO(railway);
    }

    @Transactional
    public RailwayDTO addRailway(RailwayDTO railwayDTO) {

        RailwayStation railwayStation = railwayStationRepository.findById(railwayDTO.getRailwayStationDTO().getId())
                .orElseThrow(() -> new RailwayStationNotFoundException("Railway station with id " + railwayDTO.getRailwayStationDTO().getId() + " not found"));

        Railway railway = Railway.builder()
                .railwayStation(railwayStation)
                .build();

        railwayStation.addRailway(railway);
        railwayRepository.save(railway);

        log.info("Railway with id {} has been added", railway.getId());

        return RailwayDTOModelMapper.toDTO(railway);
    }

    @Transactional
    public RailwayDTO updateRailway(RailwayDTO railwayDTO) {

        Railway oldRailway = railwayRepository
                .findById(railwayDTO.getId())
                .orElseThrow(() -> new RailwayNotFoundException(
                        "Railway with id " + railwayDTO.getId() + " not found"
                ));

        RailwayStation railwayStation = railwayStationRepository.findById(railwayDTO.getRailwayStationDTO().getId())
                .orElseThrow(() -> new RailwayStationNotFoundException("Railway station with id " + railwayDTO.getRailwayStationDTO().getId() + " not found"));

        oldRailway.setRailwayStation(railwayStation);

        log.info("Railway with id {} has been updated", oldRailway.getId());

        return RailwayDTOModelMapper.toDTO(oldRailway);
    }

    @Transactional
    public void deleteRailwayById(int id) {
        Railway railway = railwayRepository.findById(id)
                .orElseThrow(() -> new RailwayNotFoundException("Railway with id " + id + " not found"));
        railway.getRailwayStation().removeRailway(railway);

        log.info("Railway with id {} has been deleted", railway.getId());

        railwayRepository.deleteById(id);
    }

    @Transactional
    public List<RailwayDTO> findAllRailways() {

        List<Railway> railways = railwayRepository.findAll();

        List<RailwayDTO> railwayDTOS = new ArrayList<>(railways.size());

        for(Railway railway : railways){
            railwayDTOS.add(RailwayDTOModelMapper.toDTO(railway));
        }

        return railwayDTOS;
    }
}
