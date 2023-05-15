package com.example.traincaroperationsaccountingsystem.repository;

import com.example.traincaroperationsaccountingsystem.model.LoadedTrainCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadedTrainCarRepository extends JpaRepository<LoadedTrainCar, Integer> {
}
