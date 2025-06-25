package org.example.parkingspaceservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @NotBlank(message = "Zone is required")
    @Size(max = 50, message = "Zone must be at most 50 characters")
    private String zone;

    @NotBlank(message = "Type is required")
    @Size(max = 30, message = "Type must be at most 30 characters")
    private String type;


    private boolean isAvailable;

    @Size(min = 2, max = 10, message = "Number plate must be between 2 and 10 characters")
    private String numberPlate;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @Size(max = 255, message = "Description must be under 255 characters")
    private String description;

    private LocalDateTime lastUpdated;
}
