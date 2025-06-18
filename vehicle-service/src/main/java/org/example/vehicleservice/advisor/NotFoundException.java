package org.example.vehicleservice.advisor;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}


//Scenario	Exception to throw	HTTP Status
//Duplicate license plate	BadRequestException or ConflictException	400 or 409
//Vehicle not found by ID	NotFoundException	404
//Validation fails (e.g. missing fields)	Handled by Spring Validation exceptions	400
//Unexpected NullPointerException	No need custom, fallback 500	500