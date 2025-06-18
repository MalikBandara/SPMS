package org.example.vehicleservice.dto.request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    @NotBlank(message = "License plate is required")
    private String numberPlate;
    private String model;
    private String color;
    @NotNull(message = "User ID is required")
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}