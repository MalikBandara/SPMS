package org.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    private Long userId;
    private Long parkingSpaceId;
    private double amount;
    private String status;
    private String cardNumber;
    private String expiry;
    private String cvv;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
