package org.example.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numberPlate;

    private String model;
    private String color;

    @Column(nullable = false)
    private Long userId;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}