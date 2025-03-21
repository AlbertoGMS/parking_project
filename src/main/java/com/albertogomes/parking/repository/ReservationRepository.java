package com.albertogomes.parking.repository;

import com.albertogomes.parking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
