package com.connected.car.vehicle.Service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.connected.car.vehicle.entity.CarStatus;
import com.connected.car.vehicle.entity.TripDetails;

import com.connected.car.vehicle.repository.CarRepository;
import com.connected.car.vehicle.repository.CarStatusRepository;
import com.connected.car.vehicle.repository.TripDetailsRepository;
import com.connected.car.vehicle.service.impl.CarStatusServiceImpl;
import com.connected.car.vehicle.service.impl.TripDetailsImpl;
import com.connected.car.vehicle.exceptions.RecordDeactivatedException;

 

class CarStatusServiceImplTest {
	
	@Mock
	 private CarRepository carRepository;

	  @InjectMocks
	  private CarStatusServiceImpl carStatusServiceImpl;
	  
	  @Mock
	  private CarStatusRepository CarStatusRepository;
	  
	  @InjectMocks
	  private TripDetailsImpl TripDetailsImpl;
	  
	  @Mock
	  private TripDetailsRepository TripDetailsRepository;
	  
	  @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

    @Test
    void testGetCarStatusDetails_Success() {
        int carId = 1;
        List<TripDetails> tripDetailsList = Arrays.asList(new TripDetails());
        CarStatus existingCarStatus = new CarStatus();
        existingCarStatus.setActive(true);
        when(TripDetailsRepository.findAllTripDetailsByCarId(carId)).thenReturn(tripDetailsList);
        when(CarStatusRepository.findByCarId(carId)).thenReturn(existingCarStatus);
        when(CarStatusRepository.save(any())).thenReturn(existingCarStatus);
        CarStatus resultCarStatus = carStatusServiceImpl.getCarStatusDetails(carId);
        assertNotNull(resultCarStatus);
        assertEquals(existingCarStatus, resultCarStatus);
    }
    
    @Test
    void testGetCarStatusDetails_RecordDeactivation() {
        int carId = 1;
        List<TripDetails> tripDetailsList = Arrays.asList(new TripDetails());
        CarStatus deactivatedCarStatus = new CarStatus();
        deactivatedCarStatus.setActive(false); 
        when(TripDetailsRepository.findAllTripDetailsByCarId(carId)).thenReturn(tripDetailsList);
        when(CarStatusRepository.findByCarId(carId)).thenReturn(deactivatedCarStatus);
      
        assertThrows(RecordDeactivatedException.class, () -> {
            carStatusServiceImpl.getCarStatusDetails(carId);
        });
    }
    
    @Test
    void testGetCarStatusDetails_Exception() {
        int carId = 1;
        List<TripDetails> tripDetailsList = Arrays.asList(new TripDetails());
        CarStatus deactivatedCarStatus = new CarStatus();
        deactivatedCarStatus.setActive(false); 
        when(TripDetailsRepository.findAllTripDetailsByCarId(carId)).thenReturn(tripDetailsList);
        when(CarStatusRepository.findByCarId(carId)).thenReturn(deactivatedCarStatus);

        assertThrows(RuntimeException.class, () -> {
            carStatusServiceImpl.getCarStatusDetails(carId);
        });
    }

    
  
    
}

