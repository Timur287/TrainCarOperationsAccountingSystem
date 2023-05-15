package com.example.traincaroperationsaccountingsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "trainCar")
    private LoadedTrainCar loadedTrainCar;

    private String type;

    private int loadCapacity;

    private int tareWeight;
}
