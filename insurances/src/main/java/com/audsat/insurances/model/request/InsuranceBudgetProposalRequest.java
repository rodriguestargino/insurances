package com.audsat.insurances.model.request;

import java.time.LocalDate;

public class InsuranceBudgetProposalRequest {
	
	private String customerDocument;
	private LocalDate createDt;
	private LocalDate updateDt;
	private String placa;
	private boolean isActive;
	private String proposedCoverage;
	private String justification;

	public LocalDate getCreateDt() {
		return createDt;
	}

	public void setCreateDt(LocalDate createDt) {
		this.createDt = createDt;
	}

	public LocalDate getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(LocalDate updateDt) {
		this.updateDt = updateDt;
	}

	public String getCustomerDocument() {
		return customerDocument;
	}

	public void setCustomerDocument(String customerDocument) {
		this.customerDocument = customerDocument;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProposedCoverage() {
		return proposedCoverage;
	}

	public void setProposedCoverage(String proposedCoverage) {
		this.proposedCoverage = proposedCoverage;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

}
