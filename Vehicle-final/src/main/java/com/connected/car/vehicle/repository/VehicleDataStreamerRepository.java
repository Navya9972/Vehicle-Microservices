package com.connected.car.vehicle.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connected.car.vehicle.entity.VehicleDataStreamer;


public interface VehicleDataStreamerRepository extends JpaRepository<VehicleDataStreamer, Integer>{

    @Query("SELECT v from VehicleDataStreamer v where v.tripDetailsId=?1")
    Optional<VehicleDataStreamer> findByTripDetailsId(@Param("trip_details_id") Long tripDetailsId);
}
