package com.connected.car.vehicle.repository;

import com.connected.car.vehicle.entity.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public interface TripDetailsRepository extends JpaRepository<TripDetails,Long> {
   // @Query("SELECT t FROM TripDetails t where t.carId=?1")


 List<TripDetails> findByCarId(int  userId);
    public List<TripDetails> findAllTripDetailsByCarId(int carId);

    public List<TripDetails> findTopByCarIdOrderByTravelEndDateTimeDesc(int carId);
    @Query("SELECT c.initialFuelReading from TripDetails c where c.tripDetailsId=?1")
    public double findInitialFuelReadingByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

    @Query("SELECT c.currentFuelReading from TripDetails c where c.tripDetailsId=?1")
    public double findCurrentFuelReadingByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

 @Query("SELECT c.odometerStartReading from TripDetails c where c.tripDetailsId=?1")
 public double findOdometerStartReadingByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

 @Query("SELECT c.odometerEndReading from TripDetails c where c.tripDetailsId=?1")
 public double findOdometerEndReadingByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

 @Query("SELECT c.travelStartDateTime from TripDetails c where c.tripDetailsId=?1")
 public LocalDateTime findTravelStartDateByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

 @Query("SELECT c.travelEndDateTime from TripDetails c where c.tripDetailsId=?1")
 public LocalDateTime findTravelEndDateByTripDetailsId(@Param("trip_details_id")long tripDetailsId);

}


