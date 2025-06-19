package org.example.parkingspaceservice.service.impl;

import feign.FeignException;
import org.example.parkingspaceservice.advisor.ConflictException;
import org.example.parkingspaceservice.advisor.NotFoundException;
import org.example.parkingspaceservice.dto.ParkingRequest;
import org.example.parkingspaceservice.dto.ParkingResponse;
import org.example.parkingspaceservice.entity.Parking;
import org.example.parkingspaceservice.feign.UserClient;
import org.example.parkingspaceservice.repo.ParkingRepository;
import org.example.parkingspaceservice.service.ParkingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ParkingResponse createParkingSpace(ParkingRequest parkingRequest) {

        try {
            userClient.getUserById(parkingRequest.getOwnerId()); // just validate
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User not found with id " + parkingRequest.getOwnerId());
        }

        if(parkingRepository.existsByLocation(parkingRequest.getLocation())){
            throw new ConflictException("parking space with location " + parkingRequest.getLocation() + " already exists");
        }else {
            Parking parking = Parking.builder()
                    .location(parkingRequest.getLocation())
                    .zone(parkingRequest.getZone())
                    .type(parkingRequest.getType())
                    .isAvailable(parkingRequest.isAvailable())
                    .numberPlate(parkingRequest.getNumberPlate())
                    .ownerId(parkingRequest.getOwnerId())
                    .description(parkingRequest.getDescription())
                    .lastUpdated(LocalDateTime.now())
                    .build();
            parkingRepository.save(parking);
            return modelMapper.map(parking , ParkingResponse.class);

//            Parking parking = modelMapper.map(parkingRequest, Parking.class);
//            parkingRepository.save(parking);
//            return modelMapper.map(parking , ParkingResponse.class);
        }
    }

    @Override
    public List<ParkingResponse> getAllParkingSpaces() {
        if(parkingRepository.findAll().isEmpty()){
            throw new NotFoundException("no parking spaces found");
        }else {
            List<Parking> all = parkingRepository.findAll();
            return  all.stream().map(parking -> modelMapper.map(parking, ParkingResponse.class)).toList();

        }

    }

    @Override
    public void deleteParkingSpace(Long id) {

         parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("parking space not found with id " + id));
        parkingRepository.deleteById(id);
//        if(parkingRepository.findById(id).isEmpty() || !parkingRepository.existsById(id)){
//            throw  new NotFoundException("parking space not found with id " + id);
//        }else {
//            parkingRepository.deleteById(id);
//        }
    }

    @Override
    public ParkingResponse getParkingSpaceById(Long id) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("parking space not found with id " + id));
        return modelMapper.map(parking, ParkingResponse.class);
    }

    @Override
    public ParkingResponse updateParkingSpace(Long id, ParkingRequest parkingRequest) {

        try {
            userClient.getUserById(parkingRequest.getOwnerId()); // just validate
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Vehicle Owner not found with id " + parkingRequest.getOwnerId());
        }

        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking space not found wit id " + id));

                parking.setLocation(parkingRequest.getLocation());
                parking.setZone(parkingRequest.getZone());
                parking.setType(parkingRequest.getType());
                parking.setAvailable(parkingRequest.isAvailable());
                parking.setNumberPlate(parkingRequest.getNumberPlate());
                parking.setOwnerId(parkingRequest.getOwnerId());
                parking.setDescription(parkingRequest.getDescription());
                parking.setLastUpdated(LocalDateTime.now());
        parkingRepository.save(parking);
        return  modelMapper.map(parking , ParkingResponse.class);
    }

    // get all the parking spaces by zone
    @Override
    public List<ParkingResponse> findByZone(String zone) {
        if(parkingRepository.findByZone(zone).isEmpty()){
            throw new NotFoundException("no parking spaces found in zone " + zone);
        }else {
            List<Parking> byZone = parkingRepository.findByZone(zone);
            return  byZone.stream().map(parking -> modelMapper.map(parking , ParkingResponse.class)).toList();
        }

    }

    // get all the available parking spaces

    @Override
    public List<ParkingResponse> getAvailableParkingSpaces(boolean b) {
        if(parkingRepository.findByIsAvailable(b).isEmpty()){
            throw new NotFoundException("no release parking spaces found");
        }else {
            List<Parking> byAvailable = parkingRepository.findByIsAvailable(b);
            return byAvailable.stream().map(parking -> modelMapper.map(parking , ParkingResponse.class)).toList();
        }
    }


    // get all the reserved parking spaces
    @Override
    public List<ParkingResponse> getOccupiedParkingSpaces(boolean b) {
        if(parkingRepository.findByIsAvailable(b).isEmpty()){
            throw new NotFoundException("no reserved parking spaces found");
        }else {
            List<Parking> byAvailable = parkingRepository.findByIsAvailable(b);
            return byAvailable.stream().map(parking -> modelMapper.map(parking , ParkingResponse.class)).toList();
        }
    }

    @Override
    public Long getCountByZone(String zone) {
         if(parkingRepository.countByZone(zone) == 0){
             throw new NotFoundException("no parking spaces found in  " + zone);
         }else {
             return parkingRepository.countByZone(zone);
         }

    }

    @Override
    public void updateAvailability(Long id, boolean b) {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with id " + id));

        parking.setAvailable(b);
        parking.setLastUpdated(LocalDateTime.now()); // optional
        parkingRepository.save(parking);
    }

    @Override
    public List<ParkingResponse> getByLocation(String location) {
        if(!parkingRepository.existsByLocation(location)){
            throw new NotFoundException("no parking spaces found in " + location);
        }
        List<Parking> byLocation = parkingRepository.findByLocation(location);
        return byLocation.stream().map(parking -> modelMapper.map(parking , ParkingResponse.class)).toList();
    }
}
