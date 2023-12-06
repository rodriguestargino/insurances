package com.audsat.insurances.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.model.Customer;
import com.audsat.insurances.service.CustomerService;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        long customerId = 1L;
        Customer mockCustomer = new Customer(customerId, "John", "Doe", "1234567890");
        when(customerService.getCustomerById(customerId)).thenReturn(mockCustomer);

        ResponseEntity<Customer> response = customerController.findById(customerId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCustomer, response.getBody());
    }

    @Test
    public void testFindById_ReturnNotFound() {
        long customerId = 1L;
        when(customerService.getCustomerById(customerId)).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
        	customerController.findById(customerId);
        });

        // Assert the status code
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Customer not found", exception.getReason());
    }

    @Test
    public void testFindByAll() {
        List<Customer> mockCustomers = Collections.singletonList(new Customer(1L, "Jane", "Doe", "0987654321"));
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.findByAll();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    public void testFindByAll_ReturnNotFound() {
        when(customerService.getAllCustomers()).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
        	customerController.findByAll();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Customers not found", exception.getReason());
    }

    @Test
    public void testCreate() {
        Customer newCustomer = new Customer(null, "Mike", "Smith", "9876543210");
        Customer savedCustomer = new Customer(1L, "Mike", "Smith", "9876543210");
        when(customerService.createCustomer(newCustomer)).thenReturn(savedCustomer);

        ResponseEntity<Customer> response = customerController.create(newCustomer);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(savedCustomer, response.getBody());
    }

}
