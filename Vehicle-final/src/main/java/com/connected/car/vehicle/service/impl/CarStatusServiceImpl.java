package com.connected.car.vehicle.service.impl;

import com.connected.car.vehicle.entity.CarStatus;
import com.connected.car.vehicle.entity.TripDetails;
import com.connected.car.vehicle.entity.batteryStatus;
import com.connected.car.vehicle.entity.fuelStatus;
import com.connected.car.vehicle.exceptions.RecordDeactivatedException;
import com.connected.car.vehicle.exceptions.TripDetailsNotFoundException;
import com.connected.car.vehicle.repository.CarStatusRepository;
import com.connected.car.vehicle.repository.TripDetailsRepository;
import com.connected.car.vehicle.service.CarStatusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarStatusServiceImpl implements CarStatusService {
	
	private static final Logger logger=LoggerFactory.getLogger(CarStatusServiceImpl.class);
	
    @Autowired
    private TripDetailsRepository tripDetailsRepository;
    @Autowired
    private CarStatusRepository carStatusRepository;

    @Override
    public CarStatus getCarStatusDetails(int carId) {
    	try {
        List<TripDetails> tripDetails = tripDetailsRepository.findAllTripDetailsByCarId(carId);

        if (!tripDetails.isEmpty()) {
            TripDetails tripDetails1 = tripDetails.get(0);
            double currentFuel = tripDetails1.getCurrentFuelReading();
            CarStatus existingCarStatus = carStatusRepository.findByCarId(carId);
            if(!existingCarStatus.isActive())
                throw new RecordDeactivatedException("Car status is not available");

            if (existingCarStatus != null) {
                existingCarStatus.setFuelStatus(calculateFuel(currentFuel));
                existingCarStatus.setBatteryStatus(batteryStatus.HIGH);
                existingCarStatus.setLockStatus(true);
                existingCarStatus.setCarId((int) tripDetails1.getCarId());
                CarStatus carStatus1 = carStatusRepository.save(existingCarStatus);
                logger.info("Successfully updated car status details for car with carId: {}", carId);
                return carStatus1;
            } else {
                CarStatus newCarStatus = new CarStatus();
                newCarStatus.setFuelStatus(calculateFuel(currentFuel));
                newCarStatus.setCarId((int) tripDetails1.getCarId());
                newCarStatus.setBatteryStatus(batteryStatus.HIGH);
                newCarStatus.setLockStatus(true);
                CarStatus carStatus1 = carStatusRepository.save(newCarStatus);
                logger.info("Successfully created car status details for car with carId: {}", carId);
                return carStatus1;
            }
        } else {
            throw new TripDetailsNotFoundException(carId);
        }
    	}catch(Exception e)
    	{
            logger.error("Error getting car status details for car ID: {}", carId, e);
    		throw e;
    	}

    }

    private fuelStatus calculateFuel(double currentFuel) {
        if (currentFuel <= 5) {
            return fuelStatus.LOW;
        } else if (currentFuel > 5 && currentFuel <= 15) {
            return fuelStatus.MEDIUM;
        } else {
            return fuelStatus.HIGH;
        }

    }


}