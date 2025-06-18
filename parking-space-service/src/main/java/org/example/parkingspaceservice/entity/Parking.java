package org.example.parkingspaceservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Parking space unique ID

    private String location;  // Parking space location (e.g., Colombo 7)

    private String zone;  // Optional - city zone or area name

    private String type;  // Vehicle type or spot type (e.g., "Car", "Bike", "Large Vehicle")

    private boolean isAvailable;  // Spot availability status (true = available, false = occupied)

    private String numberPlate;  // Optional: if reserved, which vehicle's number plate is occupying the spot

    private Long ownerId;  // Parking owner user ID (reference to User service)

    private String description;  // Optional: additional info about the spot

    private LocalDateTime lastUpdated;  // Last update timestamp for status


}
