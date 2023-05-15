package com.example.traincaroperationsaccountingsystem.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadedTrainCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int serialNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "railway_id", referencedColumnName = "id")
    private Railway railway;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "train_car_id", referencedColumnName = "id")
    private TrainCar trainCar;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    private int cargoWeight;
    private int trainCarWeight;

    public void setTrainCar(TrainCar trainCar){
        this.trainCar = trainCar;
        trainCar.setLoadedTrainCar(this);
    }

    public void setCargo(Cargo cargo){
        this.cargo = cargo;
        cargo.setLoadedTrainCar(this);
    }
}
