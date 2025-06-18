package org.example.vehicleservice.controller;


import jakarta.validation.Valid;
import org.example.vehicleservice.dto.request.VehicleRequest;
import org.example.vehicleservice.dto.response.VehicleResponse;
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



    @PostMapping
    public ResponseEntity<VehicleResponse> registerNewVehicle(@RequestBody @Valid VehicleRequest NewVehicle){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.registerNewVehicle(NewVehicle));
    }

    @GetMapping("/{numberPlate}")
    public ResponseEntity<VehicleResponse> getVehicleByNumberPlate(@PathVariable String numberPlate){
        return ResponseEntity.ok(vehicleService.getVehicleByNumberPlate(numberPlate));
    }
}
