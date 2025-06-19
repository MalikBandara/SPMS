package org.example.parkingspaceservice.controller;


import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;
import org.example.parkingspaceservice.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping("api/v1/parking")
public class ParkingServiceController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping
    public ResponseEntity<ParkingResponse> createParkingSpace(@RequestBody ParkingRequest parkingRequest){
        ParkingResponse parkingSpace = parkingService.createParkingSpace(parkingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpace);
    }


    @GetMapping
    public ResponseEntity <List<ParkingResponse>> getAllParkingSpaces(){
        return  ResponseEntity.ok(parkingService.getAllParkingSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponse> getParkingSpaceById(@PathVariable Long id){
        return ResponseEntity.ok(parkingService.getParkingSpaceById(id));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ParkingResponse> deleteParkingSpace(@PathVariable Long id){
        parkingService.deleteParkingSpace(id);
        return ResponseEntity.noContent().build();
    }





}
