package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Insurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 885821565484421104L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(name = "proposed_coverage", nullable = false)
	private String proposedCoverage;

	@Min(value = 0)
	@Column(name = "estimated_cost", nullable = false)
	private double estimatedCost;

	@NotEmpty
	@Column(name = "justification")
	private String justification;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "create_dt", nullable = false, updatable = false)
	private LocalDate createDt;

	@Column(name = "update_dt")
	private LocalDate updateDt;

	@OneToOne(cascade = CascadeType.PERSIST) // adjust cascade type as needed
	@JoinColumn(name = "car_id")
	private Car car;

	@Column(name = "is_active")
	private boolean isActive;


	public Insurance(@NotEmpty String proposedCoverage, @Min(0) double estimatedCost, @NotEmpty String justification,
			Customer customer, LocalDate createDt, LocalDate updateDt, Car car, boolean isActive) {
		super();
		this.proposedCoverage = proposedCoverage;
		this.estimatedCost = estimatedCost;
		this.justification = justification;
		this.customer = customer;
		this.createDt = createDt;
		this.updateDt = updateDt;
		this.car = car;
		this.isActive = isActive;
	}

	public Insurance() {
		// TODO Auto-generated constructor stub
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

	@Override
	public int hashCode() {
		return Objects.hash(car, createDt, customer, estimatedCost, id, isActive, justification, proposedCoverage,
				updateDt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		return Objects.equals(car, other.car) && Objects.equals(createDt, other.createDt)
				&& Objects.equals(customer, other.customer)
				&& Double.doubleToLongBits(estimatedCost) == Double.doubleToLongBits(other.estimatedCost)
				&& Objects.equals(id, other.id) && isActive == other.isActive
				&& Objects.equals(justification, other.justification)
				&& Objects.equals(proposedCoverage, other.proposedCoverage) && Objects.equals(updateDt, other.updateDt);
	}

}
