package org.example.paymentservice.repo;

import org.example.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> getPaymentByUserId(Long userId);


}

