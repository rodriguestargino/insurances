package com.audsat.insurances.service;

import java.util.List;

import com.audsat.insurances.model.Car;

public interface CarService {

	Car getCarById(long id);
	
	Car getCarByPlaca(String placa);

	List<Car> getAllCars();

	void deleteCarById(Car car);
	
	Car createCar(Car car);


}
