package com.audsat.insurances.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.model.Claim;
import com.audsat.insurances.model.request.CarRequest;
import com.audsat.insurances.model.request.ClaimRequest;
import com.audsat.insurances.service.CarService;

@RestController
@RequestMapping("/insurance/budget/car")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> findById(@PathVariable long id) {
		Optional<Car> carOptional = carService.getCarById(id);

		return carOptional.map(car -> ResponseEntity.ok(car))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Car>> findByAll() {
		Optional<List<Car>> carsOptional = carService.getAllCars();

		return carsOptional.map(cars -> ResponseEntity.ok(cars))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cars not found"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Car> create(@RequestBody CarRequest carRequest) {

		Car car = parsetoBean(carRequest);

		Optional<Car> carOptional = carService.createCar(car);

		return carOptional.map(createdCar -> ResponseEntity.ok(createdCar))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cars coud not be create"));
	}

	private Car parsetoBean(CarRequest request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Car bean = new Car();

		bean.setFipeValue(request.getFipeValue());
		bean.setManufacturer(request.getManufacturer());
		bean.setModel(request.getModel());
		bean.setPlaca(request.getPlaca());
		bean.setYear(request.getYear());
		bean.setClaims(new ArrayList<Claim>());

		for (ClaimRequest requestClaim : request.getClaims()) {
			Claim claim = new Claim();
			claim.setTipo(requestClaim.getTipo());
			claim.setCar(bean);

			LocalDate eventDate = LocalDate.parse(requestClaim.getEventDate(),formatter);

			claim.setEventDate(eventDate);
			bean.getClaims().add(claim);
		}

		return bean;
	}

}
