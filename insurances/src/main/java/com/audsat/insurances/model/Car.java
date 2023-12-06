package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5827483130938507636L;

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
	
	@Transient
	private boolean hasSinistro; 
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Claim> claims;

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

	public Car(long carId, String string, String string2, int i) {
		// TODO Auto-generated constructor stub
	}

	public Car(Object object, String string, String string2, int i) {
		// TODO Auto-generated constructor stub
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

	public boolean hasSinistro() {
	    return !this.claims.isEmpty();
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	@Override
	public int hashCode() {
		return Objects.hash(claims, dataCadastro, drivers, fipeValue, hasSinistro, id, manufacturer, model, placa,
				year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(claims, other.claims) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(drivers, other.drivers) && Objects.equals(fipeValue, other.fipeValue)
				&& hasSinistro == other.hasSinistro && Objects.equals(id, other.id)
				&& Objects.equals(manufacturer, other.manufacturer) && Objects.equals(model, other.model)
				&& Objects.equals(placa, other.placa) && Objects.equals(year, other.year);
	}

}
