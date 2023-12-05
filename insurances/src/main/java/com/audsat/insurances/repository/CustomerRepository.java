package com.audsat.insurances.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audsat.insurances.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long id);
	
	Optional<Customer> findByCpf(String cpf);

	List<Customer> findAll();

	@SuppressWarnings("unchecked")
	Customer save(Customer customer);

	void deleteById(Long id);
}
