package com.audsat.insurances.service;

import java.util.List;
import java.util.Optional;

import com.audsat.insurances.model.Claim;

public interface ClaimService {

	Optional<Claim> getClaimById(long id);
	
	Optional<List<Claim>> getAllClaims();

	Optional<Claim> createClaim(Claim claim);
}
