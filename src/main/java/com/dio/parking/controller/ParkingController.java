package com.dio.parking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.parking.model.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	@GetMapping
	public List<Parking> findAll() {

		Parking parking = new Parking();
		parking.setColor("BRANCO");
		parking.setLicense("APT-1313");
		parking.setModel("Fiat Uno");
		parking.setState("SC");
		
		return Arrays.asList(parking, parking);
	}

}
