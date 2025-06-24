package org.example.parkingspaceservice.controller;


import jakarta.validation.Valid;
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
    public ResponseEntity<ParkingResponse> createParkingSpace(@Valid @RequestBody ParkingRequest parkingRequest){
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

    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<ParkingResponse>> finByZone(@PathVariable String zone){
        return ResponseEntity.ok(parkingService.findByZone(zone));

    }

    //get all available parkingSpace
    @GetMapping("/release")
    public ResponseEntity<List<ParkingResponse>>  getAvailableParkingSpaces(){
        return ResponseEntity.ok(parkingService.getAvailableParkingSpaces(true));
    }



    //get all  not available parkingSpace
    @GetMapping("/reserve")
    public ResponseEntity<List<ParkingResponse>>  getOccupiedParkingSpaces(){
        return ResponseEntity.ok(parkingService.getOccupiedParkingSpaces(false));
    }

    //update parking availability reserve

    @PatchMapping("/{id}/reserve")
    public ResponseEntity<String> reserveParking(@PathVariable Long id) {
        parkingService.updateAvailability(id, false); // false = occupied
        return ResponseEntity.ok("Parking space marked as reserved.");
    }

    //update parking availability

    @PatchMapping("/{id}/release")
    public ResponseEntity<String> releaseParking(@PathVariable Long id) {
        parkingService.updateAvailability(id, true); // true = available
        return ResponseEntity.ok("Parking space marked as available.");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ParkingResponse> deleteParkingSpace(@PathVariable Long id){
        parkingService.deleteParkingSpace(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingResponse> updateParkingSPace(@PathVariable Long id , @RequestBody ParkingRequest parkingRequest){
        return ResponseEntity.ok(parkingService.updateParkingSpace(id, parkingRequest));
    }

    @GetMapping("/zoneCount/{zone}")
    public  ResponseEntity<Long> getAvailableZone(@PathVariable String zone){
        return ResponseEntity.ok(parkingService.getCountByZone(zone));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ParkingResponse>> getParkingByLocation(@PathVariable String location ){
        return ResponseEntity.ok(parkingService.getByLocation(location));
    }





}
