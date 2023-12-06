package com.audsat.insurances.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.audsat.insurances.model.Customer;
import com.audsat.insurances.model.Driver;
import com.audsat.insurances.repository.CustomerRepository;
import com.audsat.insurances.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomerById(long id) {
		Customer customer = customerRepository.findById(id)
				  .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

		  return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomerById(Customer customer) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
		if (!optionalCustomer.isPresent()) {
		  throw new EntityNotFoundException("Customer not found with ID: " + customer.getId());
		}

		// Delete customer from the database
		customerRepository.delete(customer);

	}

	@Override
	public Customer createCustomer(Customer customer) {

	    customerRepository.save(customer);

	    return customer;

	}

	@Override
	public Customer getCustomerBycpf(String cpf) {
		Customer customer = customerRepository.findByCpf(cpf)
				  .orElseThrow(() -> new EntityNotFoundException("Customer not found with CPF: " + cpf));

		  return customer;
	}

}
