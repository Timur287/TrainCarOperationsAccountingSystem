package com.example.traincaroperationsaccountingsystem.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "cargo", cascade = CascadeType.ALL)
    private LoadedTrainCar loadedTrainCar;
}
