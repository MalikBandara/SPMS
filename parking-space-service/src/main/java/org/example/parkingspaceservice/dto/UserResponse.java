package org.example.parkingspaceservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
}
