package com.albertogomes.parking.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "parking_spot")
    private Long parking_spot;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot", referencedColumnName = "id", insertable = false, updatable = false)
    private ParkingSpot spot;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalValue;

    public Reservation(Long id, Integer parkingSpot, LocalDateTime startDate, LocalDateTime endDate, Double totalValue) {
        this.id = id;
        this.parking_spot = Long.valueOf(parkingSpot);
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalValue = totalValue;
    }

    public Reservation(Long parking_spot, LocalDateTime startDate, LocalDateTime endDate,  Double totalValue) {
        this.parking_spot = parking_spot;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalValue = totalValue;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParking_spot() {
        return parking_spot;
    }

    public void setParking_spot(Long parking_spot) {
        this.parking_spot = parking_spot;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", parkingSpot=" + parking_spot +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalValue=" + totalValue +
                '}';
    }
}
