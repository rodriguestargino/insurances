package com.audsat.insurances.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.model.Driver;
import com.audsat.insurances.service.InsuranceBudgetService;

class InsuranceBudgetProposalServiceImplTest {
	
	@Autowired
	private InsuranceBudgetService insuranceBudgetProposalService;
	
	@Test
	public void testCalculateInsuranceBudget() {
	    // Given
	    Car car = new Car();
	    Driver driver = new Driver();
	    LocalDate birthdate = LocalDate.of(2000, 1, 1);

	    // Define mock values
	    car.setFipeValue(10000.0);

	    driver.setBirthdate(birthdate);

	    // Expected base budget
	    double expectedBaseBudget = 10000.0 * 0.06 + 10000.0 * 0.02 ;

	    // When
	    double actualBudget = insuranceBudgetProposalService.calculateInsuranceBudget(car, driver);

	    // Then
	    assertEquals(expectedBaseBudget, actualBudget, 0.001);
	}

}
