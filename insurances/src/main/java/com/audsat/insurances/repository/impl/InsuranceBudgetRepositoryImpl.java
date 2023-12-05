package com.audsat.insurances.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.audsat.insurances.model.Insurance;
import com.audsat.insurances.repository.InsuranceBudgetRepository;

public abstract class  InsuranceBudgetRepositoryImpl implements InsuranceBudgetRepository {

	private final EntityManager entityManager;

	public InsuranceBudgetRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Insurance> findById(Long id) {
	    return Optional.ofNullable(entityManager.find(Insurance.class, id));
	}

	@Override
	public List<Insurance> findAll() {
		return entityManager.createQuery("SELECT i FROM InsuranceBudgetProposal i", Insurance.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Insurance save(Insurance insuranceBudgetProposal) {
		entityManager.persist(insuranceBudgetProposal);
		return insuranceBudgetProposal;
	}

	@Override
	public void deleteById(Long id) {
		entityManager.remove(findById(id));
	}

}
