package com.audsat.insurances.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.repository.CarRepository;
import com.audsat.insurances.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public Car getCarById(long id) {
		Car car = carRepository.findById(id)
				  .orElseThrow(() -> new EntityNotFoundException("Car not found with ID: " + id));

		  return car;
	}

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public void deleteCarById(Car car) {
		Optional<Car> optionalCar = carRepository.findById(car.getId());
		if (!optionalCar.isPresent()) {
		  throw new EntityNotFoundException("Car not found with ID: " + car.getId());
		}

		// Delete customer from the database
		carRepository.delete(car);

	}

	@Override
	public Car createCar(Car car) {
		car = carRepository.save(car);
		return car;

	}

	@Override
	public Car getCarByPlaca(String placa) {
		Car car = carRepository.findByPlaca(placa)
				  .orElseThrow(() -> new EntityNotFoundException("Car not found with Placa: " + placa));

		  return car;
	}

}
