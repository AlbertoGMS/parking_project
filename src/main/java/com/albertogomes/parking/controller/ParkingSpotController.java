package com.albertogomes.parking.controller;

import com.albertogomes.parking.entities.ParkingSpot;
import com.albertogomes.parking.service.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/space")
public class ParkingSpotController {


    private final ParkingSpotService spotService;

    public ParkingSpotController(ParkingSpotService spotService) {
        this.spotService = spotService;
    }

    @PostMapping
    public ResponseEntity<ParkingSpot> save(@RequestBody ParkingSpot parkingSpot) {
        return ResponseEntity.ok(spotService.saveParking(parkingSpot));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpot>> getAllParking() {
        //List<ParkingSpot> parkingSpotList = spotService.getAllParking(); - uncomment this and comment the line bellow get all the data.
        List<ParkingSpot> parkingSpotList = spotService.getParkingSpaceByStatus();
        return ResponseEntity.ok(parkingSpotList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpot> getByID(@PathVariable Long id){
        Optional<ParkingSpot> parkingSpot = spotService.getParkingSpaceById(id);
        return parkingSpot.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}