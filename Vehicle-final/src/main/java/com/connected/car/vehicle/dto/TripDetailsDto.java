package com.connected.car.vehicle.dto;

import jakarta.persistence.Column;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDetailsDto {
    private long tripDetailsId;
    private int carId;
    @NotNull
    private double maxSpeed;

    @NotNull
    private double minSpeed;

    @NotNull
    private LocalDateTime travelStartDateTime;
    @NotNull
    private LocalDateTime travelEndDateTime;
    @NotNull
    private double initialFuelReading;
    @NotNull
    private double currentFuelReading;
    @NotNull
    private double odometerStartReading;
    @NotNull
    private double odometerEndReading;

    private boolean active;




}
