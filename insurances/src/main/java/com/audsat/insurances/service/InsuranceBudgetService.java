package com.audsat.insurances.service;

import java.util.List;

import com.audsat.insurances.bean.InsuranceBudgetProposalBean;
import com.audsat.insurances.model.Insurance;

public interface InsuranceBudgetService {

	Insurance createInsuranceBudgetProposal(InsuranceBudgetProposalBean insuranse);

	Insurance getInsuranceBudgetProposalById(long id);

	List<Insurance> getAllInsuranceBudgetProposals();

	void updateInsuranceBudgetProposal(Insurance insuranceBudgetProposal);

	void deleteInsuranceBudgetProposalById(long id);

}
