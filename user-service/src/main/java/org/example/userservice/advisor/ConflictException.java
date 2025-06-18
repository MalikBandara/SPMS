package org.example.userservice.advisor;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
