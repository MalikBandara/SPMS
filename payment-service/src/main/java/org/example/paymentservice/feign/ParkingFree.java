package org.example.paymentservice.feign;

import org.example.paymentservice.dto.ParkingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("PARKING-SERVICE")
public interface ParkingFree {

    @GetMapping("api/v1/parking/release")
    ResponseEntity<List<ParkingResponse>> getAvailableParkingSpaces();

    @GetMapping("api/v1/parking/{id}")
    ResponseEntity<ParkingResponse> getParkingSpaceById(@PathVariable Long id);
}
