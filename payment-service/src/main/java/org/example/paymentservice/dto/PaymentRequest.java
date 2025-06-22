package org.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    private String userId;
    private String parkingSpaceId;
    private double amount;
    private String cardNumber;
    private String expiry;
    private String cvv;
}
