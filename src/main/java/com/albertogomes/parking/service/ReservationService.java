package com.albertogomes.parking.service;

import com.albertogomes.parking.entities.Reservation;
import com.albertogomes.parking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation){
        return  reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id){
        return reservationRepository.findById(id);
    }


}
