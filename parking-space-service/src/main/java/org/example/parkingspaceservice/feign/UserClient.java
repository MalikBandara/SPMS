package org.example.parkingspaceservice.feign;


import org.example.parkingspaceservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("api/v1/user/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
