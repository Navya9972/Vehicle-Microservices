package com.connected.car.vehicle.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSummaryDto {

    private long carSummaryId;
    private long tripDetailsId;
    private double mileage;
    private double avgMileage;
    private double fuelConsumption;
    private double totalDistance;
    private String idleTime;
    private String parking_time;
    private int duration;
    private boolean active;
}
