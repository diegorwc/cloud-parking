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
import com.dio.parking.repository.ParkingRepository;

@Service
public class ParkingService {
	
	private final ParkingRepository parkingRepository;
	private static Map<String, Parking> parkingMap = new HashMap();
	
	
	public ParkingService(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}
	
	public List<Parking> findAll() {
		return parkingRepository.findAll();		
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-","");
	}

	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id)); 
	}

	public Parking create(Parking parkingCreate) {	
		//verificar se placa já existe
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
//		Parking parking = findById(id);
		parkingRepository.deleteById(id);	
	}

	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setLicense(parkingCreate.getLicense());
		parking.setModel(parkingCreate.getModel());
		parking.setState(parkingCreate.getState());		
		parkingRepository.save(parking);
		return parking;
	}

	public Parking exit(String id) {		
		//TO-DO
		//recuperar o estacionado
		//atualizar data de saida
		//calcular o valor
		Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        return parking;		
	}
}
