package org.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleDto {
    private Long vehicleId;
    private String NumberPlate;
    private String model;
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}
