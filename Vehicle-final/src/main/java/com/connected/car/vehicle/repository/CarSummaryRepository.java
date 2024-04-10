package com.connected.car.vehicle.repository;

import com.connected.car.vehicle.entity.CarSummary;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarSummaryRepository extends JpaRepository<CarSummary,Long> {

    public CarSummary findCarSummaryByTripDetailsId(long tripDetailsId);

    @Query("SELECT c.fuelConsumption from CarSummary c where c.tripDetailsId=?1")
    public double findFuelConsumptionByTripDetailsId(@Param("trip_details_id") long tripDetailsId);


    @Query("SELECT c.totalDistance from CarSummary c where c.tripDetailsId=?1 ")
    public double findTotalDistanceByTripDetailsId(@Param("trip_details_id") long tripDetailsId);
    
    @Query("SELECT cs FROM CarSummary cs JOIN TripDetails td ON cs.tripDetailsId = td.tripDetailsId " +
            "WHERE td.travelStartDateTime BETWEEN :startDate AND :endDate")
	public List<CarSummary> FindByTravelStartDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    public CarSummary findByTripDetailsId(long tripDetailsId);


}
