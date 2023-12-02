package com.audsat.insurances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audsat.insurances.model.InsuranceBudget;

@Repository
public interface InsuranceBudgetRepository extends JpaRepository<InsuranceBudget, Long> {

}
