package com.audsat.insurances.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.audsat.insurances.model.Claim;
import com.audsat.insurances.repository.ClaimRepository;

public abstract class  ClaimRepositoryImpl implements ClaimRepository {

	private final EntityManager entityManager;

	public ClaimRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Claim> findById(Long id) {
	    return Optional.ofNullable(entityManager.find(Claim.class, id));
	}
	
	@Override
	public List<Claim> findAll() {
		return entityManager.createQuery("SELECT i FROM Claim i", Claim.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Claim save(Claim claim) {
		entityManager.persist(claim);
		return claim;
	}

	@Override
	public void deleteById(Long id) {
		entityManager.remove(findById(id));
	}

}
