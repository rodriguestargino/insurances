package com.audsat.insurances.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.service.CarService;

public class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Mock the car service to return an existing car
        Long carId = 1L;
        Optional<Car> mockCar = Optional.of(new Car(carId, "Honda", "Civic", 2023));
        when(carService.getCarById(carId)).thenReturn(mockCar);

        ResponseEntity<Car> response = carController.findById(carId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCar.get(), response.getBody());
    }
    
    @Test
    public void testFindById_ReturnNotFound() {
        Long carId = 1L;
        when(carService.getCarById(carId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            carController.findById(carId);
        });

        // Assert the status code
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Car not found", exception.getReason());
    }


    @Test
    public void testFindByAll() {
        // Mock the car service to return a non-empty list of cars
        Optional<List<Car>> mockCars = Optional.of(Collections.singletonList(new Car(1L, "Toyota", "Camry", 2022)));
        when(carService.getAllCars()).thenReturn(mockCars);

        ResponseEntity<List<Car>> response = carController.findByAll();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCars.get(), response.getBody());
    }


    @Test
    public void testFindByAll_ReturnNotFound() {
        when(carService.getAllCars()).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            carController.findByAll();
        });

        // Assert the status code 
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Cars not found", exception.getReason());
    }



//    @Test
//    public void testCreate_salvarCar() {
//        Car newCar = new Car(null, "Hyundai", "Sonata", 2021);
//        Car savedCar = new Car(1L, "Hyundai", "Sonata", 2021);
//        Optional<Car> savedCarOptional = Optional.of(savedCar);
//        when(carService.createCar(newCar)).thenReturn(savedCarOptional);
//
//        ResponseEntity<Car> response = carController.create(newCar);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertNotNull(response.getBody());
//        assertEquals(savedCar, response.getBody());
//    }

}
