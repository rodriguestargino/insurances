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

import com.audsat.insurances.bean.InsuranceBudgetProposalBean;
import com.audsat.insurances.model.Insurance;
import com.audsat.insurances.model.request.InsuranceBudgetProposalRequest;
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
	public void testFindById_whenInsuranceFound_shouldReturnOkAndInsurance() {
	    // Given
	    long insuranceId = 1L;
	    Insurance mockInsurance = new Insurance(); // mock insurance object
	    when(insuranceBudgetProposalService.getInsuranceBudgetProposalById(insuranceId)).thenReturn(mockInsurance);

	    // When
	    ResponseEntity<Insurance> response = insuranceBudgetProposalController.findById(insuranceId);

	    // Then
	    assertEquals(200, response.getStatusCodeValue());
	    assertNotNull(response.getBody());
	    assertEquals(mockInsurance, response.getBody());
	}

	@Test
	public void testFindById_whenInsuranceNotFound_shouldReturnNotFound() {
		// Given
		long insuranceId = 1L;
		when(insuranceBudgetProposalService.getInsuranceBudgetProposalById(insuranceId)).thenReturn(null);

		// When
		ResponseEntity<Insurance> response = insuranceBudgetProposalController.findById(insuranceId);

		// Then
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	public void testFindAll_whenInsurancesFound_shouldReturnOkAndInsuranceList() {
	    // Given
	    List<Insurance> mockInsurances = Collections.singletonList(new Insurance()); // mock list of insurances
	    when(insuranceBudgetProposalService.getAllInsuranceBudgetProposals()).thenReturn(mockInsurances);

	    // When
	    ResponseEntity<List<Insurance>> response = insuranceBudgetProposalController.findByAll();

	    // Then
	    assertEquals(200, response.getStatusCodeValue());
	    assertNotNull(response.getBody());
	    assertEquals(mockInsurances, response.getBody());
	}

	@Test
	public void testFindAll_whenNoInsurancesFound_shouldReturnNotFound() {
		// Given
		when(insuranceBudgetProposalService.getAllInsuranceBudgetProposals()).thenReturn(Collections.emptyList());

		// When
		ResponseEntity<List<Insurance>> response = insuranceBudgetProposalController.findByAll();

		// Then
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	public void testCreate_whenValidRequest_shouldReturnOkAndInsurance() {
	    // Given
	    InsuranceBudgetProposalRequest request = new InsuranceBudgetProposalRequest(); // mock request object
	    Insurance mockInsurance = new Insurance(); // mock insurance object
	    InsuranceBudgetProposalBean bean = null ; // create bean from request
	    when(insuranceBudgetProposalService.createInsuranceBudgetProposal(bean)).thenReturn(mockInsurance);

	    // When
	    ResponseEntity<Insurance> response = insuranceBudgetProposalController.create(request);

	    // Then
	    assertEquals(200, response.getStatusCodeValue());
	    assertNotNull(response.getBody());
	    assertEquals(mockInsurance, response.getBody());
	}

//	@Test
//	public void testCreate_whenMissingCustomer_shouldReturnBadRequest() {
//	    // Given
//	    InsuranceBudgetProposalRequest request = new InsuranceBudgetProposalRequest(); // create request without customer
//	    InsuranceBudgetProposalBean requestBean = new InsuranceBudgetProposalBean(); // create request without customer
//
//	    
//	    when(insuranceBudgetProposalService.createInsuranceBudgetProposal(request)).thenThrow(new IllegalArgumentException("Missing customer information"));
//
//	    // When
//	    ResponseEntity<Insurance> response = insuranceBudgetProposalController.create(requestBean);
//
//	    // Then
//	    assertEquals(400, response.getStatusCodeValue());
//	}

	@Test
	public void testDeleteById_shouldReturnOk() {
		// Given
		long insuranceId = 1L;

		// When
		ResponseEntity<Void> response = insuranceBudgetProposalController.deleteById(insuranceId);

		// Then
		assertEquals(200, response.getStatusCodeValue());
	}

}
