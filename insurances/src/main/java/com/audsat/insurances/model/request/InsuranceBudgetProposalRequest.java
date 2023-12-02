package com.audsat.insurances.model.request;

public class InsuranceBudgetProposalRequest {

	private String proposedCoverage;
	private double estimatedCost;
	private String justification;

	public String getProposedCoverage() {
		return proposedCoverage;
	}

	public void setProposedCoverage(String proposedCoverage) {
		this.proposedCoverage = proposedCoverage;
	}

	public double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

}
