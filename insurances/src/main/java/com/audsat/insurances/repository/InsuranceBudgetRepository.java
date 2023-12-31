package com.audsat.insurances.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audsat.insurances.model.Insurance;

@Repository
public interface InsuranceBudgetRepository extends JpaRepository<Insurance, Long> {

	Optional<Insurance> findById(Long id);

	List<Insurance> findAll();

	@SuppressWarnings("unchecked")
	Insurance save(Insurance insuranceBudgetProposal);

	void deleteById(Long id);
}
