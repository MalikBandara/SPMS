package org.example.vehicleservice.controller;


import org.example.vehicleservice.dto.VehicleRequest;
import org.example.vehicleservice.dto.VehicleResponse;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.repo.VehicleRepository;
import org.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/vehicle")
@RestController
public class VehicleController {


    @Autowired
    private VehicleService vehicleService;



    @PostMapping("add")
    private ResponseEntity<VehicleResponse> registerNewVehicle(@RequestBody VehicleRequest NewVehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.registerNewVehicle(NewVehicle));
    }
}
