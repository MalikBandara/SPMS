package org.example.vehicleservice.service.impl;

import feign.FeignException;
import org.example.vehicleservice.advisor.ConflictException;
import org.example.vehicleservice.advisor.NotFoundException;
import org.example.vehicleservice.dto.request.VehicleRequest;
import org.example.vehicleservice.dto.response.VehicleResponse;
import org.example.vehicleservice.dto.user.UserResponse;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.feign.UserClient;
import org.example.vehicleservice.repo.VehicleRepository;
import org.example.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private UserClient userClient;


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VehicleResponse registerNewVehicle(VehicleRequest newVehicle) {

        try {
            userClient.getUserById(newVehicle.getUserId()); // just validate
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User not found with id " + newVehicle.getUserId());
        }

        if (vehicleRepository.existsByNumberPlate(newVehicle.getNumberPlate())) {
            throw new ConflictException("Vehicle with license plate " + newVehicle.getNumberPlate() + " already exists");
        }

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


    @Override
    public VehicleResponse getVehicleByNumberPlate(String numberPlate) {
        if(!vehicleRepository.existsByNumberPlate(numberPlate)){
            throw new NotFoundException("Vehicle with license plate" + numberPlate + "does not exist");
        }else {
            Vehicle vehicle = vehicleRepository.findByNumberPlate(numberPlate).get();
            return modelMapper.map(vehicle, VehicleResponse.class);

        }
    }

    @Override
    public List<VehicleResponse>  getAllVehicles() {
        try {
            List<Vehicle> all = vehicleRepository.findAll();
            return all.stream().map(vehicle -> modelMapper.map(vehicle, VehicleResponse.class)).toList();
        }catch(Exception e ){
            throw new NotFoundException("No vehicle found");
        }
    }

    @Override
    public void deleteVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found with number plate: " + vehicleId));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleResponse updateVehicleById(String numberPlate, VehicleRequest newVehicle) {
        Vehicle vehicle =  vehicleRepository.findByNumberPlate(numberPlate)
                .orElseThrow(()-> new NotFoundException("vehicle with number plate " + numberPlate + "does not exist"));

        vehicle.setNumberPlate(newVehicle.getNumberPlate());
        vehicle.setModel(newVehicle.getModel());
        vehicle.setColor(newVehicle.getColor());
        vehicle.setUserId(newVehicle.getUserId());
        vehicle.setCheckInTime(newVehicle.getCheckInTime());
        vehicle.setCheckOutTime(newVehicle.getCheckOutTime());

        vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle , VehicleResponse.class);

    }




}
