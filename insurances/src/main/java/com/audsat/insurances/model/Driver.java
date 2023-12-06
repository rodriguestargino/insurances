package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "document", length = 100)
	@NotEmpty(message = "{campo.driver.document.obrigatorio}")
	private String document;

	@Column(name = "birthdate")
	private LocalDate birthdate;
	
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
	private List<Claim> claims;

	@ManyToMany
	@JoinTable(name = "driver_cars", joinColumns = { @JoinColumn(name = "driver_id") }, inverseJoinColumns = {
			@JoinColumn(name = "car_id") })
	private List<Car> cars;
	
	@Transient
	private Integer idade;
	
	@Transient
	private boolean hasSinistro;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getIdade() {
		if (birthdate == null) {
			throw new IllegalArgumentException("Data de nascimento não pode ser null");
		}

		LocalDate now = LocalDate.now();
	    Period diff = Period.between(birthdate, now);
	    return diff.getYears();
	}
	
	public boolean hasSinistro() {
	    return !this.claims.isEmpty();
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthdate, cars, claims, dataCadastro, document, hasSinistro, id, idade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(birthdate, other.birthdate) && Objects.equals(cars, other.cars)
				&& Objects.equals(claims, other.claims) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(document, other.document) && hasSinistro == other.hasSinistro
				&& Objects.equals(id, other.id) && Objects.equals(idade, other.idade);
	}

}
