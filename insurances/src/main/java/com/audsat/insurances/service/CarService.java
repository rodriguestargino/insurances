package com.audsat.insurances.service;

import java.util.List;
import java.util.Optional;

import com.audsat.insurances.model.Car;

public interface CarService {

	Optional<Car> getCarById(long id);
	
	Optional<Car> getCarByPlaca(String placa);

	Optional<List<Car>> getAllCars();

	void deleteCarById(Car car);
	
	Optional<Car> createCar(Car car);


}
