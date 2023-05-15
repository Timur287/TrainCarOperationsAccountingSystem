package com.example.traincaroperationsaccountingsystem.repository;

import com.example.traincaroperationsaccountingsystem.model.TrainCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCarRepository extends JpaRepository<TrainCar, Integer> {
}
