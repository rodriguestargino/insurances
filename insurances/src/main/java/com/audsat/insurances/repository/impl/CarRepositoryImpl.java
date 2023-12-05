package com.audsat.insurances.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.repository.CarRepository;

public abstract class  CarRepositoryImpl implements CarRepository {

	private final EntityManager entityManager;

	public CarRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Car> findById(Long id) {
	    return Optional.ofNullable(entityManager.find(Car.class, id));
	}
	
	@Override
	public Optional<Car> findByPlaca(String placa) {
	    return Optional.ofNullable(entityManager.find(Car.class, placa));
	}

	@Override
	public List<Car> findAll() {
		return entityManager.createQuery("SELECT i FROM Car i", Car.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Car save(Car car) {
		entityManager.persist(car);
		return car;
	}

	@Override
	public void deleteById(Long id) {
		entityManager.remove(findById(id));
	}

}
