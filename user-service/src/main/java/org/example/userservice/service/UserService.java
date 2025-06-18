package org.example.userservice.service;

import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest newUser);

    UserResponse getUserById(Long id);
}
