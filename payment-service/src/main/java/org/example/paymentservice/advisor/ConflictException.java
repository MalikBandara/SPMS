package org.example.paymentservice.advisor;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
