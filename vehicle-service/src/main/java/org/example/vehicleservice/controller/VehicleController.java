package org.example.vehicleservice.controller;


import jakarta.validation.Valid;
import org.example.vehicleservice.dto.request.VehicleRequest;
import org.example.vehicleservice.dto.response.VehicleResponse;
import org.example.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/vehicle")
@RestController
public class VehicleController {


    // need to add user using fien


    @Autowired
    private VehicleService vehicleService;



    @PostMapping
    public ResponseEntity<VehicleResponse> registerNewVehicle( @RequestBody @Valid VehicleRequest NewVehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.registerNewVehicle(NewVehicle));
    }

    @GetMapping("/{numberPlate}")
    public ResponseEntity<VehicleResponse> getVehicleByNumberPlate(@PathVariable String numberPlate){
        return ResponseEntity.ok(vehicleService.getVehicleByNumberPlate(numberPlate));
    }

    @GetMapping
    public ResponseEntity <List<VehicleResponse>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicleById(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{numberPlate}")
    public ResponseEntity<VehicleResponse> updateVehicleById(@PathVariable String numberPlate , @RequestBody VehicleRequest vehicleRequest){
        return ResponseEntity.ok( vehicleService.updateVehicleById(numberPlate, vehicleRequest));
    }
}
