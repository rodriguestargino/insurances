package com.audsat.insurances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.model.Customer;
import com.audsat.insurances.service.CustomerService;

@RestController
@RequestMapping("/insurance/budget/customer")
public class CustomerController {

	private final CustomerService customerService;
	
	@Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable long id) {
		Customer customer = customerService.getCustomerById(id);

		if (customer == null) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		} else {
			return ResponseEntity.ok(customer);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> findByAll() {
		List<Customer> customer = customerService.getAllCustomers();

		if (customer == null || customer.isEmpty()) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customers not found");
		} else {
			return ResponseEntity.ok(customer);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    	
		Customer customerEntity = customerService.createCustomer(customer);
        return ResponseEntity.ok(customerEntity);
    }

}
