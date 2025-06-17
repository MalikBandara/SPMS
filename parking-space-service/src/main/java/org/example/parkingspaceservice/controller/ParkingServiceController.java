package org.example.parkingspaceservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingServiceController {


    @GetMapping("/parking")
    public String getParking() {
        return "parking";
    }
}
