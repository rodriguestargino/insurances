package com.audsat.insurances.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.audsat.insurances.bean.InsuranceBudgetProposalBean;
import com.audsat.insurances.enums.ProposedCoverage;
import com.audsat.insurances.model.Insurance;
import com.audsat.insurances.repository.InsuranceBudgetRepository;
import com.audsat.insurances.service.InsuranceBudgetService;

@Service
public class InsuranceBudgetProposalServiceImpl implements InsuranceBudgetService{
	
		private final InsuranceBudgetRepository insuranceBudgetProposalRepository;

	    public InsuranceBudgetProposalServiceImpl(InsuranceBudgetRepository insuranceBudgetProposalRepository) {
	        this.insuranceBudgetProposalRepository = insuranceBudgetProposalRepository;
	    }
	
	    @Override
	    public Insurance createInsuranceBudgetProposal(InsuranceBudgetProposalBean insuranseProposta) {
		        
		        Insurance insuranse = new Insurance();
		        parseAndCreateInsurance(insuranseProposta, insuranse);

		        return insuranceBudgetProposalRepository.save(insuranse);
	    }
	    
	    public Insurance parseAndCreateInsurance(InsuranceBudgetProposalBean InsuranceBean, Insurance insurance ) {
	        
	    	if (!isValidProposedCoverage(InsuranceBean.getProposedCoverage())) {
	            throw new IllegalArgumentException("Invalid proposed coverage: " + InsuranceBean.getProposedCoverage());
	        }

	        if (InsuranceBean.getEstimatedCost() <= 0) {
	            throw new IllegalArgumentException("Estimated cost must be positive");
	        }

	        if (InsuranceBean.getJustification().isEmpty()) {
	            throw new IllegalArgumentException("Justification cannot be empty");
	        }

	        insurance.setCustomer(InsuranceBean.getCustomer());
	        insurance.setCreateDt(InsuranceBean.getCreateDt());
	        insurance.setUpdateDt(InsuranceBean.getUpdateDt());
	        insurance.setCar(InsuranceBean.getCar());
	        insurance.setActive(InsuranceBean.isActive());

	        insurance.setProposedCoverage(InsuranceBean.getProposedCoverage());
	        insurance.setEstimatedCost(InsuranceBean.getEstimatedCost());
	        insurance.setJustification(InsuranceBean.getJustification());

	        return insurance;
	    }
	    
	    private boolean isValidProposedCoverage(String proposedCoverage) {
	        return Arrays.asList(ProposedCoverage.FULL_COVERAGE.getLabel(), ProposedCoverage.LIABILITY_COVERAGE.getLabel(), ProposedCoverage.COLLISION_COVERAGE.getLabel()).contains(proposedCoverage);
	    }

	    @Override
	    public Insurance getInsuranceBudgetProposalById(long id) {
	        return insuranceBudgetProposalRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<Insurance> getAllInsuranceBudgetProposals() {
	        return insuranceBudgetProposalRepository.findAll();
	    }

	    @Override
	    public void updateInsuranceBudgetProposal(Insurance insuranceBudgetProposal) {
	    	insuranceBudgetProposalRepository.save(insuranceBudgetProposal);
	    }

	    @Override
	    public void deleteInsuranceBudgetProposalById(long id) {
	    	insuranceBudgetProposalRepository.deleteById(id);
	    }

}
