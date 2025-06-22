package org.example.paymentservice.feign;

import org.example.paymentservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface UserClient {
    @GetMapping("api/v1/user/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) ;
}
