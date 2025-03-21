package com.albertogomes.parking.service;

import com.albertogomes.parking.entities.ParkingSpot;
import com.albertogomes.parking.enumeration.ParkingSpotStatus;
import com.albertogomes.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    private final ParkingRepository parkingRepository;


    public ParkingSpotService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public ParkingSpot saveParking(ParkingSpot parkingSpot) {
        return parkingRepository.save(parkingSpot);
    }

    public List<ParkingSpot> getAllParking() {
        return parkingRepository.findAll();
    }
    public List<ParkingSpot> getParkingSpaceByStatus(){
        String status = "DISPONIVEL";
        return parkingRepository.findByStatus(ParkingSpotStatus.valueOf(status));
    }

    public Optional<ParkingSpot> getParkingSpaceById(Long id){
        return parkingRepository.findById(id);
    }

}
