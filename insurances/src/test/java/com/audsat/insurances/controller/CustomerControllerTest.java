package com.audsat.insurances.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
    public void testFindById_whenCustomerFound_shouldReturnOkAndCustomer() {
        // Given
        long customerId = 1L;
        Customer mockCustomer = new Customer(customerId, "John", "Doe", "1234567890");
        when(customerService.getCustomerById(customerId)).thenReturn(mockCustomer);

        // When
        ResponseEntity<Customer> response = customerController.findById(customerId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCustomer, response.getBody());
    }

    @Test
    public void testFindById_whenCustomerNotFound_shouldReturnNotFound() {
        // Given
        long customerId = 1L;
        when(customerService.getCustomerById(customerId)).thenReturn(null);

        // When
        ResponseEntity<Customer> response = customerController.findById(customerId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testFindByAll_whenCustomersFound_shouldReturnOkAndCustomerList() {
        // Given
        List<Customer> mockCustomers = Collections.singletonList(new Customer(1L, "Jane", "Doe", "0987654321"));
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        // When
        ResponseEntity<List<Customer>> response = customerController.findByAll();

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    public void testFindByAll_whenNoCustomersFound_shouldReturnNotFound() {
        // Given
        when(customerService.getAllCustomers()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<Customer>> response = customerController.findByAll();

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreate_whenCustomerCreated_shouldReturnOkAndCustomer() {
        // Given
        Customer newCustomer = new Customer(null, "Mike", "Smith", "9876543210");
        Customer savedCustomer = new Customer(1L, "Mike", "Smith", "9876543210");
        when(customerService.createCustomer(newCustomer)).thenReturn(savedCustomer);

        // When
        ResponseEntity<Customer> response = customerController.create(newCustomer);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(savedCustomer, response.getBody());
    }

}
