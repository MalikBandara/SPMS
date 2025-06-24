package org.example.paymentservice.service;

import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PaymentResponse;
import org.example.paymentservice.entity.Payment;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByUserId(Long userId);

    List<Payment> getAllPayment();
}
