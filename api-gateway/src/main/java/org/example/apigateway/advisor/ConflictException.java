package org.example.apigateway.advisor;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
