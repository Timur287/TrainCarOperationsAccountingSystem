package com.example.traincaroperationsaccountingsystem.repository;

import com.example.traincaroperationsaccountingsystem.model.RailwayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayStationRepository extends JpaRepository<RailwayStation, Integer> {
}
