package org.example.parkingspaceservice.service;

import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;

import java.util.List;

public interface ParkingService {
    ParkingResponse createParkingSpace(ParkingRequest parkingRequest);

    List<ParkingResponse> getAllParkingSpaces();

    void deleteParkingSpace(Long id);

}


