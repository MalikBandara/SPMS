package org.example.parkingspaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingRequest {

    private String location;

    private String zone;

    private String type;

    private boolean isAvailable;

    private String numberPlate;

    private Long ownerId;

    private String description;

    private LocalDateTime lastUpdated;
}
