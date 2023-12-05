package com.audsat.insurances.service;

import java.util.List;

import com.audsat.insurances.model.Customer;

public interface CustomerService {

	Customer getCustomerById(long id);
	
	Customer getCustomerBycpf(String cpf);

	List<Customer> getAllCustomers();

	void deleteCustomerById(Customer customer);
	
	Customer createCustomer(Customer customer);


}
