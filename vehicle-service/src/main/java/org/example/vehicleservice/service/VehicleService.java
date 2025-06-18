package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.VehicleRequest;
import org.example.vehicleservice.dto.VehicleResponse;



public interface VehicleService {


    VehicleResponse registerNewVehicle(VehicleRequest newVehicle);
}


