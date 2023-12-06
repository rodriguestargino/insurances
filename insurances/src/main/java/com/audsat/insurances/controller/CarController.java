package com.audsat.insurances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audsat.insurances.model.Car;
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
		Car car = carService.getCarById(id);

		if (car == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(car);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Car>> findByAll() {
		List<Car> car = carService.getAllCars();

		if (car == null || car.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(car);
		}
	}

	@PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
    	
		Car carEntity = carService.createCar(car);
        return ResponseEntity.ok(carEntity);
    }

}
