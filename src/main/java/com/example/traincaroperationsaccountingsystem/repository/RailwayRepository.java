package com.example.traincaroperationsaccountingsystem.repository;

import com.example.traincaroperationsaccountingsystem.model.Railway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayRepository extends JpaRepository<Railway, Integer> {
}
