package com.audsat.insurances.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.audsat.insurances.bean.InsuranceBudgetProposalBean;
import com.audsat.insurances.enums.ProposedCoverage;
import com.audsat.insurances.exception.RegraNegocioException;
import com.audsat.insurances.model.Car;
import com.audsat.insurances.model.Driver;
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
	    
	    public Insurance parseAndCreateInsurance(InsuranceBudgetProposalBean insuranceBean, Insurance insurance ) {
	        
	    	if (!isValidProposedCoverage(insuranceBean.getProposedCoverage())) {
	            throw new IllegalArgumentException("Invalid proposed coverage: " + insuranceBean.getProposedCoverage());
	        }

	        if (insuranceBean.getJustification() == null || insuranceBean.getJustification().isEmpty()) {
	            throw new RegraNegocioException("Justificativa Necessária");
	        }

	        insurance.setCustomer(insuranceBean.getCustomer());
	        insurance.setCreateDt(insuranceBean.getCreateDt());
	        insurance.setUpdateDt(insuranceBean.getUpdateDt());
	        insurance.setCar(insuranceBean.getCar().get());
	        insurance.setActive(insuranceBean.isActive());

	        insurance.setProposedCoverage(insuranceBean.getProposedCoverage());
	        insurance.setJustification(insuranceBean.getJustification());

	        insurance.setEstimatedCost(calculateInsuranceBudget(insuranceBean.getCar().get(), insuranceBean.getCustomer().getDriver()));
	        
	        return insurance;
	    }
	    
	    private boolean isValidProposedCoverage(String proposedCoverage) {
	        return Arrays.asList(ProposedCoverage.FULL_COVERAGE.getLabel(), ProposedCoverage.LIABILITY_COVERAGE.getLabel(), ProposedCoverage.COLLISION_COVERAGE.getLabel()).contains(proposedCoverage);
	    }
	    
	    public double calculateInsuranceBudget(Car car, Driver driver) {

	        double baseBudget = car.getFipeValue() * 0.06;

	        // Adicione os custos adicionais para cada risco mapeado

	        if (driver.getIdade() <= 25) {
	            baseBudget += baseBudget * 0.02;
	        }

	        if (driver.hasSinistro()) {
	            baseBudget += baseBudget * 0.02;
	        }

	        if (car.hasSinistro()) {
	            baseBudget += baseBudget * 0.02;
	        }

	        return baseBudget;
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
