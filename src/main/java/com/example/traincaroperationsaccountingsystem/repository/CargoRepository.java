package com.example.traincaroperationsaccountingsystem.repository;

import com.example.traincaroperationsaccountingsystem.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
