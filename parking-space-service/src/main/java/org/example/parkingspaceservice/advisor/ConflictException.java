package org.example.parkingspaceservice.advisor;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
