package com.audsat.insurances.model;

import java.io.Serializable;
import java.sql.Driver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Car implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454577723996516749L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "model", length = 100)
	@NotEmpty(message = "{campo.customer.model.obrigatorio}")
	private String model;
	
	@NotEmpty(message = "{campo.customer.model.manufacturer.obrigatorio}")
	@Column(name = "manufacturer", nullable = false)
	@NotNull
	private String manufacturer;
	
	@NotEmpty(message = "{campo.customer.model.year.obrigatorio}")
	@Column(name = "year", nullable = false)
	@NotNull
    private int year;
    
	@NotEmpty(message = "{campo.customer.model.fipeValue.obrigatorio}")
	@Column(name = "fipeValue", nullable = false)
	@NotNull
	private double fipeValue;
    
    @ManyToMany(mappedBy = "cars")
	private List<Driver> drivers; // List of drivers associated with this car
	
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	public Car() {
		super();
	}

	 public Car(long id, String model, String manufacturer, int year, double fipeValue) {
	        this.id = id;
	        this.model = model;
	        this.manufacturer = manufacturer;
	        this.year = year;
	        this.fipeValue = fipeValue;
	        this.drivers = new ArrayList<>();
	    }
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getFipeValue() {
		return fipeValue;
	}

	public void setFipeValue(double fipeValue) {
		this.fipeValue = fipeValue;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
