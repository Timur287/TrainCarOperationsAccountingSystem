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
public class RailwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "railwayStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Railway> railways;

    public void addRailway(Railway railway){
        railways.add(railway);
        railway.setRailwayStation(this);
    }

    public void removeRailway(Railway railway){
        railways.remove(railway);
        railway.setRailwayStation(null);
    }
}
