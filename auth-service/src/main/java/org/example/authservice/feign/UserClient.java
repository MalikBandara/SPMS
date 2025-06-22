package org.example.authservice.feign;

import org.example.authservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="user-service")
public interface UserClient {
    @GetMapping("api/v1/user/email/{email}")
    UserResponse getUserByEmail(@PathVariable("email") String email);
}
