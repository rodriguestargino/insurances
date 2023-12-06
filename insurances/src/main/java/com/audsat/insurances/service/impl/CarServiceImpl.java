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
	public Optional<Car> getCarById(long id) {
	    return carRepository.findById(id);
	}


	@Override
	public Optional<List<Car>> getAllCars() {
	    return Optional.ofNullable(carRepository.findAll());
	}


	@Override
	public void deleteCarById(Car car) {
		Optional<Car> optionalCar = carRepository.findById(car.getId());
		if (!optionalCar.isPresent()) {
		  throw new EntityNotFoundException("Car not found with ID: " + car.getId());
		}

		carRepository.delete(car);

	}
	
	@Override
	public Optional<Car> createCar(Car car) {
	    try {
	        return Optional.of(carRepository.save(car));
	    } catch (Exception e) {
	        return Optional.empty();
	    }
	}


	@Override
	public Optional<Car> getCarByPlaca(String placa) {
		return carRepository.findByPlaca(placa);

	}

}
