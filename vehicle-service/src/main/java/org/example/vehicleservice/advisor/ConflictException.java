package org.example.vehicleservice.advisor;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
