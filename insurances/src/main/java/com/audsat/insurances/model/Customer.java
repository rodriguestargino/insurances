package com.audsat.insurances.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1288462622002229765L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nome", length = 100)
	@NotEmpty(message = "{campo.customer.nome.obrigatorio}")
	private String nome;
	
	@CPF
	@NotEmpty(message = "{campo.customer.cpf.obrigatorio}")
	@Column(name = "cpf", nullable = false, length = 11)
	@NotNull
	private String cpf;
	
	@Email
	@Column(name = "email")
	@NotNull
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id")
    private Driver driver; // FK to Driver table
	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	public Customer() {
		super();
	}

	public Customer(String nome) {
		super();
		this.nome = nome;
	}

	public Customer(Integer id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Customer(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}

	public Customer(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public Set<Pedido> getPedidos() {
//		return pedidos;
//	}
//
//	public void setPedidos(Set<Pedido> pedidos) {
//		this.pedidos = pedidos;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
