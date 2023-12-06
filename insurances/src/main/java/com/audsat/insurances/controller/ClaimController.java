package com.audsat.insurances.controller;

import java.util.List;
import java.util.Optional;

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

import com.audsat.insurances.model.Claim;
import com.audsat.insurances.service.ClaimService;

@RestController
@RequestMapping("/insurance/budget/claim")
public class ClaimController {

	private final ClaimService claimService;

	@Autowired
	public ClaimController(ClaimService claimService) {
		this.claimService = claimService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Claim> findById(@PathVariable long id) {
		Optional<Claim> carOptional = claimService.getClaimById(id);

		return carOptional.map(car -> ResponseEntity.ok(car))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found"));

	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Claim>> findByAll() {
	    Optional<List<Claim>> claimRepository = claimService.getAllClaims();

	    return claimRepository.map(claims -> ResponseEntity.ok(claims))
	                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claims not found"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Claim> create(@RequestBody Claim claim) {
	    Optional<Claim> claimRepository = claimService.createClaim(claim);

	    return claimRepository.map(claims -> ResponseEntity.ok(claims))
	    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Claim coud not be create"));
	}


}
