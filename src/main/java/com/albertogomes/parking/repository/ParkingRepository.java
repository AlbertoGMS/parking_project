package com.albertogomes.parking.repository;

import com.albertogomes.parking.entities.ParkingSpot;
import com.albertogomes.parking.enumeration.ParkingSpotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<ParkingSpot, Long> {
    @Query("SELECT value FROM ParkingSpot value WHERE value.status = :status")
    List<ParkingSpot> findByStatus(@Param("status") ParkingSpotStatus status);
}
