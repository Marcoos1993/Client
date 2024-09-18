package com.devsuperior.clients.dto;

import java.time.LocalDate;

import com.devsuperior.clients.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class ClientDTO {

	private Long id;
	
	@NotBlank(message = "Campo requerido")
	@Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
	private String name;
	
	@NotBlank(message = "Campo requerido")
	private String cpf;
	private Double income;
	
	@PastOrPresent
	private LocalDate birthDate;
	private Integer children;
	
	
	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}


	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getCpf() {
		return cpf;
	}


	public Double getIncome() {
		return income;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public Integer getChildren() {
		return children;
	}
	
	
	
}

