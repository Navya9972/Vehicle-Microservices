package com.connected.car.vehicle.dto;


import com.connected.car.vehicle.entity.batteryStatus;
import com.connected.car.vehicle.entity.fuelStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStatusDto {
    private long carStatusId;
    private com.connected.car.vehicle.entity.fuelStatus fuelStatus;
    private int car_id;
    private boolean lockStatus;
    private com.connected.car.vehicle.entity.batteryStatus batteryStatus;
    private boolean active;

}
