package com.audsat.insurances.service;

import java.util.List;

import com.audsat.insurances.model.InsuranceBudget;

public interface InsuranceBudgetService {

	InsuranceBudget createInsuranceBudgetProposal(String proposedCoverage, double estimatedCost,
			String justification);

	InsuranceBudget getInsuranceBudgetProposalById(long id);

	List<InsuranceBudget> getAllInsuranceBudgetProposals();

	void updateInsuranceBudgetProposal(InsuranceBudget insuranceBudgetProposal);

	void deleteInsuranceBudgetProposalById(long id);

}
