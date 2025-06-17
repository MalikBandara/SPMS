package org.example.vehicleservice.controller;


import jakarta.ws.rs.NotFoundException;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/vehicle")
@RestController
public class VehicleController {


    @Autowired
    private VehicleRepo vehicleRepo;


//    @GetMapping("vehicle")
//    public String getVehicle() {
//
//        return "vehicle";
//    }

    @PostMapping("add")
    private ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle){
           vehicleRepo.save(vehicle);
           return new ResponseEntity<>("Vehicle added successfully", HttpStatus.CREATED);
    }
}
