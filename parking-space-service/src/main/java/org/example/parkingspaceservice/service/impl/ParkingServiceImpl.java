package org.example.parkingspaceservice.service.impl;

import org.example.parkingspaceservice.advisor.ConflictException;
import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;
import org.example.parkingspaceservice.entity.Parking;
import org.example.parkingspaceservice.repo.ParkingRepository;
import org.example.parkingspaceservice.service.ParkingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParkingServiceImpl implements ParkingService {


    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ParkingResponse createParkingSpace(ParkingRequest parkingRequest) {
        if(parkingRepository.existsByLocation(parkingRequest.getLocation())){
            throw new ConflictException("parking space with location " + parkingRequest.getLocation() + " already exists");
        }else {
            Parking parking = Parking.builder()
                    .location(parkingRequest.getLocation())
                    .zone(parkingRequest.getZone())
                    .type(parkingRequest.getType())
                    .isAvailable(parkingRequest.isAvailable())
                    .numberPlate(parkingRequest.getNumberPlate())
                    .ownerId(parkingRequest.getOwnerId())
                    .description(parkingRequest.getDescription())
                    .lastUpdated(parkingRequest.getLastUpdated())
                    .build();
            parkingRepository.save(parking);
            return modelMapper.map(parking , ParkingResponse.class);

//            Parking parking = modelMapper.map(parkingRequest, Parking.class);
//            parkingRepository.save(parking);
//            return modelMapper.map(parking , ParkingResponse.class);
        }
    }
}
