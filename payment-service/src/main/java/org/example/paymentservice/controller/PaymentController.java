package org.example.paymentservice.controller;


import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PaymentResponse;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        PaymentResponse payment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.status(201).body(payment);
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<Payment> getReceipt(@PathVariable String id) {
        return ResponseEntity.ok( paymentService.getPaymentById(id));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        List<Payment> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }


}
