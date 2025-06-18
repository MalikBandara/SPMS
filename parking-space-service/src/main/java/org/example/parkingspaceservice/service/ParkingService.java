package org.example.parkingspaceservice.service;

import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;

public interface ParkingService {
    ParkingResponse createParkingSpace(ParkingRequest parkingRequest);
}


