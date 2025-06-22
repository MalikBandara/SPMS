package org.example.paymentservice.service;

import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);
}
