package com.audsat.insurances.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.audsat.insurances.model.Claim;
import com.audsat.insurances.repository.ClaimRepository;
import com.audsat.insurances.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

	private final ClaimRepository claimRepository;

	public ClaimServiceImpl(ClaimRepository claimRepository) {
		this.claimRepository = claimRepository;
	}

	@Override
	public Optional<Claim> getClaimById(long id) {
	    return claimRepository.findById(id);
	}


	@Override
	public Optional<List<Claim>> getAllClaims() {
	    return Optional.ofNullable(claimRepository.findAll());
	}


	@Override
	public Optional<Claim> createClaim(Claim claim) {
	    try {
	        return Optional.of(claimRepository.save(claim));
	    } catch (Exception e) {
	        return Optional.empty();
	    }
	}


}
