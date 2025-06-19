package org.example.parkingspaceservice.repo;

import org.example.parkingspaceservice.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    boolean existsByLocation(String location);

    List<Parking> findByZone(String zone);

    List<Parking> findByIsAvailable(boolean available);

}

