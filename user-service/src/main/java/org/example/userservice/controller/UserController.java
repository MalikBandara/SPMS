package org.example.userservice.controller;

import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest newUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse userById = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userById);
    }
}
