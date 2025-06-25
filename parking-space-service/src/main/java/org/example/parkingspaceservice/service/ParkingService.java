package org.example.parkingspaceservice.service;

import org.example.parkingspaceservice.advisor.NotFoundException;
import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;
import org.example.parkingspaceservice.entity.Parking;

import java.util.List;

public interface ParkingService {
    ParkingResponse createParkingSpace(ParkingRequest parkingRequest);

    List<ParkingResponse> getAllParkingSpaces();

    void deleteParkingSpace(Long id);

    ParkingResponse getParkingSpaceById(Long id);

    ParkingResponse updateParkingSpace(Long id, ParkingRequest parkingRequest);

    List<ParkingResponse> findByZone(String zone);

    List<ParkingResponse> getAvailableParkingSpaces(boolean b);


    List<ParkingResponse> getOccupiedParkingSpaces(boolean b);

    Long getCountByZone(String zone);

    void updateAvailabilityRelease(Long id, boolean b);

    void updateAvailabilityReserve(Long id, boolean b);

    List<ParkingResponse> getByLocation(String location);
}


