package com.example.traincaroperationsaccountingsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Railway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "railway_station_id", referencedColumnName = "id")
    private RailwayStation railwayStation;

    @OneToMany(mappedBy = "railway", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  List<LoadedTrainCar> train;
    private void addLoadedTrainCar(LoadedTrainCar loadedTrainCar) {

        train.add(loadedTrainCar);
        loadedTrainCar.setRailway(this);
    }

    public void addLoadedTrainCars(List<LoadedTrainCar> loadedTrainCars){
        for(LoadedTrainCar loadedTrainCar : loadedTrainCars){
            addLoadedTrainCar(loadedTrainCar);
        }
    }

    public void removeLoadedTrainCars(List<LoadedTrainCar> loadedTrainCars){
        train.removeAll(loadedTrainCars);
    }
}