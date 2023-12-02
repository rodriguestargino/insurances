package com.audsat.insurances.service.impl;

import java.util.List;

import com.audsat.insurances.model.InsuranceBudget;
import com.audsat.insurances.repository.InsuranceBudgetRepository;
import com.audsat.insurances.service.InsuranceBudgetService;

public class InsuranceBudgetProposalServiceImpl implements InsuranceBudgetService{
	
		private final InsuranceBudgetRepository insuranceBudgetProposalRepository;

	    public InsuranceBudgetProposalServiceImpl(InsuranceBudgetRepository insuranceBudgetProposalRepository) {
	        this.insuranceBudgetProposalRepository = insuranceBudgetProposalRepository;
	    }
	
	    @Override
	    public InsuranceBudget createInsuranceBudgetProposal(String proposedCoverage, double estimatedCost, String justification) {
	    	InsuranceBudget insuranceBudgetProposal = new InsuranceBudget(proposedCoverage, estimatedCost, justification);
	        return insuranceBudgetProposalRepository.save(insuranceBudgetProposal);
	    }

	    @Override
	    public InsuranceBudget getInsuranceBudgetProposalById(long id) {
	        return insuranceBudgetProposalRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<InsuranceBudget> getAllInsuranceBudgetProposals() {
	        return insuranceBudgetProposalRepository.findAll();
	    }

	    @Override
	    public void updateInsuranceBudgetProposal(InsuranceBudget insuranceBudgetProposal) {
	    	insuranceBudgetProposalRepository.save(insuranceBudgetProposal);
	    }

	    @Override
	    public void deleteInsuranceBudgetProposalById(long id) {
	    	insuranceBudgetProposalRepository.deleteById(id);
	    }

}
