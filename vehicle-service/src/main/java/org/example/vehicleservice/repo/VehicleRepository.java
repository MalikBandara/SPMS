package org.example.vehicleservice.repo;

import org.example.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByNumberPlate(String numberPlate);
    boolean existsByNumberPlate(String numberPlate);



}
