package org.example.parkingspaceservice.repo;

import org.example.parkingspaceservice.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    boolean existsByLocation(String location);

}

