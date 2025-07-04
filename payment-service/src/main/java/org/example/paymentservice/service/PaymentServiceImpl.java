package org.example.paymentservice.service;

import feign.FeignException;
import org.example.paymentservice.advisor.NotFoundException;
import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PaymentResponse;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.feign.ParkingFree;
import org.example.paymentservice.feign.UserClient;
import org.example.paymentservice.repo.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingFree parkingFree;

    @Autowired
    private UserClient userClient;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {


        try {
            userClient.getUserById(paymentRequest.getUserId()); // just validate

        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User not found with id " + paymentRequest.getUserId());
        }

        try {
            parkingFree.getParkingSpaceById(paymentRequest.getParkingSpaceId());
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Parking space not found with ID: " + paymentRequest.getParkingSpaceId());
        }





        if(paymentRequest.getUserId() == null){
            throw new NotFoundException("User id not found " + paymentRequest.getUserId());
        }

        boolean isValidCard = paymentRequest.getCardNumber() != null && paymentRequest.getCardNumber().startsWith("4");
        String status = isValidCard ? "SUCCESS" : "FAILED";

        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Calculate duration in hours
        long minutes = java.time.Duration.between(paymentRequest.getEntryTime(), paymentRequest.getExitTime()).toMinutes();
        double ratePerHour = 50.0;
        double hours = Math.ceil(minutes / 60.0); // round up to next hour
        double totalAmount = hours * ratePerHour;


        Payment payment = Payment.builder()
                .paymentId(paymentId)
                .userId(paymentRequest.getUserId())
                .parkingSpaceId(paymentRequest.getParkingSpaceId())
                .amount(totalAmount)
                .timestamp(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);
        return new PaymentResponse(
                payment.getPaymentId(),
                status,
                isValidCard ? "Payment processed successfully" : "Payment failed",
                "/api/v1/payment/receipt/" + payment.getPaymentId()
        );
    }

    @Override
    public Payment getPaymentById(String id) {
        return   paymentRepository.findById(id)
                 .orElseThrow(()-> new NotFoundException("payment with id " + id + "does not exist"));

    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        if(paymentRepository.getPaymentByUserId(userId).isEmpty()){
            throw new NotFoundException("payment with user id " + userId + "does not exist");
        }else {
            return paymentRepository.getPaymentByUserId(userId);
        }

    }

    @Override
    public List<Payment> getAllPayment() {
        if(paymentRepository.findAll().isEmpty()){
            throw new NotFoundException("No Payment found");
        }else {
            List<Payment> all = paymentRepository.findAll();
            return all;

        }
    }
}
