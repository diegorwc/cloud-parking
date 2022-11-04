package com.dio.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.dio.parking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
	
	

}
