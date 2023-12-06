package com.audsat.insurances.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audsat.insurances.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

	Optional<Claim> findById(Long id);
	
	List<Claim> findAll();

	@SuppressWarnings("unchecked")
	Claim save(Claim car);

	void deleteById(Long id);
}
