package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insuranse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8992909347886365373L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Customer customer; // FK to Customer table
    private LocalDate createDt;
    private LocalDate updateDt;
    private Car car; // FK to Car table
    private boolean isActive;
	
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
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
