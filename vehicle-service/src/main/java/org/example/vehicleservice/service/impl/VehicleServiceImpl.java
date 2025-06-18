package org.example.vehicleservice.service.impl;

import org.example.vehicleservice.advisor.ConflictException;
import org.example.vehicleservice.dto.VehicleRequest;
import org.example.vehicleservice.dto.VehicleResponse;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.repo.VehicleRepository;
import org.example.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VehicleResponse registerNewVehicle(VehicleRequest newVehicle) {
        if(vehicleRepository.existsByNumberPlate(newVehicle.getNumberPlate())){
            throw new ConflictException("Vehicle with license plate" + newVehicle.getNumberPlate() + "already exists");
        }else {
            Vehicle vehicle = Vehicle.builder()
                    .numberPlate(newVehicle.getNumberPlate())
                    .model(newVehicle.getModel())
                    .color(newVehicle.getColor())
                    .userId(newVehicle.getUserId())
                    .checkInTime(newVehicle.getCheckInTime())
                    .checkOutTime(newVehicle.getCheckOutTime())
                    .build();
            vehicleRepository.save(vehicle);

            return modelMapper.map(vehicle, VehicleResponse.class);

        }
    }
}
