package org.example.vehicleservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Vehicle {

    @Id
    private Long vehicleId;
    private String NumberPlate;
    private String model;
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}
