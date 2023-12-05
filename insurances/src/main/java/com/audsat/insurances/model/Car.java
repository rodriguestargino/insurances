package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454577723996516749L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "model", length = 100)
	@NotEmpty(message = "{campo.car.model.obrigatorio}")
	private String model;

	@NotEmpty(message = "{campo.car.model.manufacturer.obrigatorio}")
	@Column(name = "manufacturer", nullable = false)
	@NotNull
	private String manufacturer;
	
	@NotEmpty(message = "{campo.car.model.palca.obrigatorio}")
	@Column(name = "placa", nullable = false)
	@NotNull
	private String placa;

	@NotNull(message = "{campo.car.model.year.obrigatorio}")
	@Column(name = "year", nullable = false)
	@NotNull
	private Integer year;

	@NotNull(message = "{campo.car.model.fipeValue.obrigatorio}")
	@Column(name = "fipeValue", nullable = false)
	@NotNull
	private Double fipeValue;

    @ManyToMany(mappedBy = "cars")
	private List<Driver> drivers;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public Car() {
		super();
	}

	public Car(long id, String model, String manufacturer, int year, Double fipeValue, List<Driver> drivers) {
		this.id = id;
		this.model = model;
		this.manufacturer = manufacturer;
		this.year = year;
		this.fipeValue = fipeValue;
	    this.drivers = drivers;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
