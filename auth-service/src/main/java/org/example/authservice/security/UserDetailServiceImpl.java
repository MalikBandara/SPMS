package org.example.authservice.security;


import org.example.authservice.dto.UserResponse;
import org.example.authservice.feign.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;


//    http://localhost:8080/auth/login

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userByEmail = userClient.getUserByEmail(username);

        if (userByEmail==null){
            throw new UsernameNotFoundException("user is not found ");

        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .build();
    }
}
