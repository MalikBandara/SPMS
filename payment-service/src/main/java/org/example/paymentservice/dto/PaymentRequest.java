package org.example.paymentservice.dto;

import jakarta.validation.constraints.*;
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

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Parking space ID is required")
    private Long parkingSpaceId;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|SUCCESS|FAILED)$", message = "Status must be PENDING, SUCCESS, or FAILED")
    private String status;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "(0[1-9]|1[0-2])\\/\\d{2}", message = "Expiry must be in MM/YY format")
    private String expiry;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    @NotNull(message = "Entry time is required")
    private LocalDateTime entryTime;

    @NotNull(message = "Exit time is required")
    private LocalDateTime exitTime;
}
