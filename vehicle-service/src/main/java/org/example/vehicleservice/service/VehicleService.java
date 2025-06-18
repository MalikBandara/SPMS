package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.request.VehicleRequest;
import org.example.vehicleservice.dto.response.VehicleResponse;



public interface VehicleService {


    VehicleResponse registerNewVehicle(VehicleRequest newVehicle);

    VehicleResponse getVehicleByNumberPlate(String numberPlate);
}


