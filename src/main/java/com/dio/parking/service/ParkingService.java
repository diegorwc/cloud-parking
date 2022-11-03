package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dio.parking.exception.ParkingNotFoundException;
import com.dio.parking.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap();
	
	static {
		var id = getUUID();
		var id2 = getUUID();
		Parking parking = new Parking(id, "ARR-2901", "SC", "MONZA", "AZUL");
		Parking parking2 = new Parking(id2, "RTR-2391", "PB", "VECTRA", "BRANCO");
		parkingMap.put(id, parking);
		parkingMap.put(id2,  parking2);
	}
	
	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-","");
	}

	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking; 
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		Parking parking = findById(id);
		parkingMap.remove(id);		
	}

	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setLicense(parkingCreate.getLicense());
		parking.setModel(parkingCreate.getModel());
		parking.setState(parkingCreate.getState());
		parkingMap.replace(id, parking);
		return parking;
	}
}
