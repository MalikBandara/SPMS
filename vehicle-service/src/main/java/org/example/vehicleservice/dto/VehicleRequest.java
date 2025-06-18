package org.example.vehicleservice.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
}