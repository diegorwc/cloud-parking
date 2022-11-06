package com.dio.parking.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.dio.parking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudParkingApplicationTests extends AbstractContainerBase {

	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenfindAllThenCheckResult() {
		RestAssured.given()
			.auth()
			.basic("user", "Dio@123456")
			.when()
			.get("/parking")
			.then()			
			.statusCode(200);
	}
	
	@Test
	void whenCreateThenCheckIsCreated() {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("VERMELHO");
		createDTO.setLicense("LAM-1124");
		createDTO.setModel("FUSCA");
		createDTO.setState("RS");
		
		RestAssured.given()
			.auth()
			.basic("user", "Dio@123456")
			.when()
			.contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parking")
			.then()
			.statusCode(org.springframework.http.HttpStatus.CREATED.value())
			.body("state", Matchers.equalTo("RS"));			
	}

}
