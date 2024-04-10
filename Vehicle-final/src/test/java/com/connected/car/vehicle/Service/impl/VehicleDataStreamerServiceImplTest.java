package com.connected.car.vehicle.Service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.connected.car.vehicle.dto.VehicleDataStreamerDto;
import com.connected.car.vehicle.entity.VehicleDataStreamer;
import com.connected.car.vehicle.exceptions.EngineAlreadyInActiveException;
import com.connected.car.vehicle.exceptions.TripDetailsNotFoundException;
import com.connected.car.vehicle.repository.VehicleDataStreamerRepository;
import com.connected.car.vehicle.service.impl.CarSummaryImpl;
import com.connected.car.vehicle.service.impl.VehicleDataStreamerServiceimpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleDataStreamerServiceImplTest {

    @Mock
    private VehicleDataStreamerRepository vehicleDataStreamerRepository;

    @Mock
    private CarSummaryImpl carSummaryServiceImpl;

    @InjectMocks
    private VehicleDataStreamerServiceimpl vehicleDataStreamerService;

    @Test
    @Order(1)
    void testSetEngineStatusInActive() {
        Long tripDetailId = 1L;
        VehicleDataStreamer vehicleDataStreamer = new VehicleDataStreamer();
        vehicleDataStreamer.setEngineActive(true);
        when(vehicleDataStreamerRepository.findByTripDetailsId(tripDetailId))
                .thenReturn(Optional.of(vehicleDataStreamer));
        assertDoesNotThrow(() -> vehicleDataStreamerService.setEngineStatusInActive(tripDetailId));
        assertFalse(vehicleDataStreamer.isEngineActive());
        verify(vehicleDataStreamerRepository, times(1)).save(vehicleDataStreamer);
    }

    @Test
    @Order(2)
    void testSetEngineStatusInActive_ThrowException() {
        Long tripDetailId = 1L;
        VehicleDataStreamer vehicleDataStreamer = new VehicleDataStreamer();
        vehicleDataStreamer.setEngineActive(false);
        when(vehicleDataStreamerRepository.findByTripDetailsId(tripDetailId))
                .thenReturn(Optional.of(vehicleDataStreamer));

        assertThrows(EngineAlreadyInActiveException.class,
                () -> vehicleDataStreamerService.setEngineStatusInActive(tripDetailId));
        verify(vehicleDataStreamerRepository, never()).save(any());
    }

    @Test
    @Order(3)
    void testSetEngineStatusInActive_ThrowNotFoundException() {
        Long tripDetailId = 1L;
        when(vehicleDataStreamerRepository.findByTripDetailsId(tripDetailId)).thenReturn(Optional.empty());

        assertThrows(TripDetailsNotFoundException.class,
                () -> vehicleDataStreamerService.setEngineStatusInActive(tripDetailId));
        verify(vehicleDataStreamerRepository, never()).save(any());
    }
}
