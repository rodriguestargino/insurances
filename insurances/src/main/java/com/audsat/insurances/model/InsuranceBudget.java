package com.audsat.insurances.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class InsuranceBudget implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5216045470346636748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String proposedCoverage;
	private double estimatedCost;
	private String justification;
	private Car car;
	
	public InsuranceBudget(String proposedCoverage, double estimatedCost, String justification) {
		this.proposedCoverage = proposedCoverage;
		this.estimatedCost = estimatedCost;
		this.justification = justification;
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
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	

}
