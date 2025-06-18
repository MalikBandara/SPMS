package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.request.VehicleRequest;
import org.example.vehicleservice.dto.response.VehicleResponse;

import java.util.List;


public interface VehicleService {


    VehicleResponse registerNewVehicle(VehicleRequest newVehicle);

    VehicleResponse getVehicleByNumberPlate(String numberPlate);

    List<VehicleResponse> getAllVehicles();

}


