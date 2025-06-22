package org.example.paymentservice.service;

import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PaymentResponse;
import org.example.paymentservice.entity.Payment;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    Payment getPaymentById(String id);
}
