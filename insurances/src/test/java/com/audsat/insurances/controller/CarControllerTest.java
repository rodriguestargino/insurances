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
    public void testFindById_whenCarFound_shouldReturnOkAndCar() {
        long carId = 1L;
        Car mockCar = new Car(carId, "Honda", "Civic", 2023);
        when(carService.getCarById(carId)).thenReturn(mockCar);

        ResponseEntity<Car> response = carController.findById(carId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCar, response.getBody());
    }

    @Test
    public void testFindById_whenCarNotFound_shouldReturnNotFound() {
        long carId = 1L;
        when(carService.getCarById(carId)).thenReturn(null);

        ResponseEntity<Car> response = carController.findById(carId);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testFindByAll_whenCarsFound_shouldReturnOkAndCarList() {
        List<Car> mockCars = Collections.singletonList(new Car(1L, "Toyota", "Camry", 2022));
        when(carService.getAllCars()).thenReturn(mockCars);

        ResponseEntity<List<Car>> response = carController.findByAll();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockCars, response.getBody());
    }

    @Test()
    public void testFindByAll_whenNoCarsFound_shouldReturnNotFound() {
        when(carService.getAllCars()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Car>> response = carController.findByAll();

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreate_whenCarCreated_shouldReturnOkAndCar() {
        Car newCar = new Car(null, "Hyundai", "Sonata", 2021);
        Car savedCar = new Car(1L, "Hyundai", "Sonata", 2021);
        when(carService.createCar(newCar)).thenReturn(savedCar);

        ResponseEntity<Car> response = carController.create(newCar);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(savedCar, response.getBody());
    }

}
