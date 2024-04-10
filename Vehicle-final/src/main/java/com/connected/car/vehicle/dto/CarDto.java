package com.connected.car.vehicle.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@NoArgsConstructor
@Getter
@Setter
public class CarDto {
	private int carId;
	@NotNull
	private String model;
	private String description;
	@NotNull
	@Size(min = 10, max = 10, message = "Invalid registration number, length should be 10")
	private String registrationNumber;
	@NotNull
	private String manufacturer;
	@NotNull
	private int manufactureYear;
	@NotNull
	private String engineType;
	@Size(min = 17, max = 17, message = "Invalid vin number")
	private String vinNumber;
	@NotNull
	private int userId;
	private boolean active;



}
