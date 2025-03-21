package com.albertogomes.parking.entities;
import com.albertogomes.parking.enumeration.ParkingSpotStatus;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ParkingSpot implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private Integer number;
    private String type;
    private Double pricePerHour;
    @Enumerated(EnumType.STRING)
    private ParkingSpotStatus status;

    public ParkingSpot(Long id, Integer number, String type, Double pricePerHour, ParkingSpotStatus status) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.status = status;
    }

    public ParkingSpot(Integer numberInput, ParkingSpotStatus status) {
    }

    public void update(Long id, ParkingSpotStatus status){
        this.id = id;
        this.status = status;
    }

    public ParkingSpot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public ParkingSpotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", status=" + status +
                '}';
    }
}
