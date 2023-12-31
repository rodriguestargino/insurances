package com.audsat.insurances.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audsat.insurances.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findById(Long id);
	
	Optional<Car> findByPlaca(String placa);

	List<Car> findAll();

	@SuppressWarnings("unchecked")
	Car save(Car car);

	void deleteById(Long id);
}
