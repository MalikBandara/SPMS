package org.example.vehicleservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String numberPlate;
    private String model;
    private String color;
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}