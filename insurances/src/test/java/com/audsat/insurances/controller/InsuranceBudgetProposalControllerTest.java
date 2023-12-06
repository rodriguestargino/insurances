package com.audsat.insurances.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
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

import com.audsat.insurances.model.Insurance;
import com.audsat.insurances.service.impl.InsuranceBudgetProposalServiceImpl;

class InsuranceBudgetProposalControllerTest {

	@Mock
	private InsuranceBudgetProposalServiceImpl insuranceBudgetProposalService;
	
	@InjectMocks
	private InsuranceBudgetProposalController insuranceBudgetProposalController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void testFindById() {
		long insuranceId = 1L;
		Insurance mockInsurance = new Insurance(); // mock insurance object
		when(insuranceBudgetProposalService.getInsuranceBudgetProposalById(insuranceId)).thenReturn(mockInsurance);

		ResponseEntity<Insurance> response = insuranceBudgetProposalController.findById(insuranceId);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(mockInsurance, response.getBody());
	}

	@Test
	public void testFindById_ReturnNotFound() {
		long insuranceId = 1L;
		when(insuranceBudgetProposalService.getInsuranceBudgetProposalById(insuranceId)).thenReturn(null);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			insuranceBudgetProposalController.findById(insuranceId);
		});

		// Assert the status code
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
		assertEquals("Insurance not found", exception.getReason());

	}

	@Test
	public void testFindAll() {
		List<Insurance> mockInsurances = Collections.singletonList(new Insurance()); // mock list of insurances
		when(insuranceBudgetProposalService.getAllInsuranceBudgetProposals()).thenReturn(mockInsurances);

		ResponseEntity<List<Insurance>> response = insuranceBudgetProposalController.findByAll();

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(mockInsurances, response.getBody());
	}

	@Test
	public void testFindAll_ReturnNotFound() {
		when(insuranceBudgetProposalService.getAllInsuranceBudgetProposals()).thenReturn(Collections.emptyList());

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			insuranceBudgetProposalController.findByAll();
		});

		// Assert the status code
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
		assertEquals("Insurances not found", exception.getReason());

	}


	@Test
	public void testDeleteById() {
	    long insuranceId = 1L;

	    Insurance mockInsurance = mock(Insurance.class);
	    when(insuranceBudgetProposalService.getInsuranceBudgetProposalById(insuranceId)).thenReturn(mockInsurance);

	    ResponseEntity<Void> response = insuranceBudgetProposalController.deleteById(insuranceId);

	    assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
}
