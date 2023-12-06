package com.audsat.insurances.model.request;

import java.util.List;

public class CarRequest {
	
	private String model;
	private String manufacturer;
	private String placa;
	private Integer year;
	private Double fipeValue;
	
	private List<ClaimRequest> claims;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getFipeValue() {
		return fipeValue;
	}
	public void setFipeValue(Double fipeValue) {
		this.fipeValue = fipeValue;
	}
	public List<ClaimRequest> getClaims() {
		return claims;
	}
	public void setClaims(List<ClaimRequest> claims) {
		this.claims = claims;
	}

}
