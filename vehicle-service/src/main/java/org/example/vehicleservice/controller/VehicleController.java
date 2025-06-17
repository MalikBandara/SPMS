package org.example.vehicleservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vehicle")
@RestController
public class VehicleController {


    @GetMapping("/vehicle")
    public String getVehicle() {
        return "vehicle";
    }
}
