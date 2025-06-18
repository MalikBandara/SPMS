package org.example.userservice.service.impl;

import com.netflix.discovery.converters.Auto;
import org.example.userservice.advisor.ConflictException;
import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;
import org.example.userservice.entity.User;
import org.example.userservice.repo.UserRepository;
import org.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserResponse createUser(UserRequest newUser) {

        if(userRepository.existsByEmail(newUser.getEmail())){
            throw new ConflictException("User with email " + newUser.getEmail() + " already exists");
        }else {
            modelMapper.map(newUser, User.class);
            return modelMapper.map(userRepository.save(modelMapper.map(newUser, User.class)), UserResponse.class);
        }

    }

    @Override
    public UserResponse getUserById(Long id) {
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            return modelMapper.map(user, UserResponse.class);
        }else {
            throw new ConflictException("User with id " + id + " does not exist");
        }
    }
}
