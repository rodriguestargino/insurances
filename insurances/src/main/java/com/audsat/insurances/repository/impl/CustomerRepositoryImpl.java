package com.audsat.insurances.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.audsat.insurances.model.Customer;
import com.audsat.insurances.repository.CustomerRepository;

public abstract class  CustomerRepositoryImpl implements CustomerRepository {

	private final EntityManager entityManager;

	public CustomerRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Customer> findById(Long id) {
	    return Optional.ofNullable(entityManager.find(Customer.class, id));
	}
	
	@Override
	public Optional<Customer> findByCpf(String cpf) {
	    return Optional.ofNullable(entityManager.find(Customer.class, cpf));
	}

	@Override
	public List<Customer> findAll() {
		return entityManager.createQuery("SELECT i FROM Customer i", Customer.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer save(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}

	@Override
	public void deleteById(Long id) {
		entityManager.remove(findById(id));
	}

}
