package org.example.vehicleservice.dto.request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank(message = "License plate is required")
    @Size(min = 2, max = 10, message = "License plate must be between 2 and 10 characters")
    private String numberPlate;

    @Size(max = 50, message = "Model name must be under 50 characters")
    private String model;

    @Size(max = 30, message = "Color must be under 30 characters")
    private String color;

    @NotNull(message = "User ID is required")
    private Long userId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;
}
