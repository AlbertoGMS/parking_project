package com.albertogomes.parking.controller;

import com.albertogomes.parking.entities.Reservation;
import com.albertogomes.parking.repository.ParkingRepository;
import com.albertogomes.parking.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.saveReservation(reservation), ResponseEntity.ok().body(reservation).getStatusCode());
    }
    @PutMapping
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.saveReservation(reservation), ResponseEntity.ok().body(reservation).getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getByID(@PathVariable Long id){
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


}
