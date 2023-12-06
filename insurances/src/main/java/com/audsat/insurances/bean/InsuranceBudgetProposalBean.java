package com.audsat.insurances.bean;

import java.time.LocalDate;
import java.util.Optional;

import com.audsat.insurances.model.Car;
import com.audsat.insurances.model.Customer;

public class InsuranceBudgetProposalBean {
	
	private Customer customer;
	private LocalDate createDt;
	private LocalDate updateDt;
	
	private Optional<Car> car;
	private boolean isActive;
	private String proposedCoverage;
	private double estimatedCost;
	private String justification;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
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
	public Optional<Car> getCar() {
		return car;
	}
	public void setCar(Optional<Car> car) {
		this.car = car;
	}
	

}
