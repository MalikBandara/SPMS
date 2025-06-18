package org.example.userservice.service.impl;
import org.example.userservice.advisor.ConflictException;
import org.example.userservice.advisor.NotFoundException;
import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;
import org.example.userservice.entity.User;
import org.example.userservice.repo.UserRepository;
import org.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
        return modelMapper.map(user, UserResponse.class);

    }

    @Override
    public List<UserResponse> getAllUsers() {

        if(userRepository.findAll().isEmpty()){
            throw new NotFoundException("No users found");
        }else {
            List<User> all = userRepository.findAll();
            return all.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
        }

    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        userRepository.deleteById(id);

    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        if(userRepository.findById(id).isEmpty() || !userRepository.existsById(id)){
            throw new NotFoundException("user not found with id " + id );
        }else {
            User user = userRepository.findById(id).get();
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            user.setRole(userRequest.getRole());
            user.setPassword(userRequest.getPassword());
            return modelMapper.map(userRepository.save(user), UserResponse.class);
        }

    }
}
