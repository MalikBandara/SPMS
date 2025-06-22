package org.example.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {

    @Id
    private String paymentId;
    private Long userId;
    private Long parkingSpaceId;
    private double amount;
    private LocalDateTime timestamp;
}


//{
//        "userId": "USER001",
//        "parkingSpaceId": "PARK001",
//        "amount": 150.00,
//        "cardNumber": "4444 1111 1111 1111",
//        "expiry": "12/26",
//        "cvv": "123"
//        }

