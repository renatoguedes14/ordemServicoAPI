package com.renato.os.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numSequencial;
	private String nome;
	@CPF
	private String cpf;
	private String telefone;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer numSequencial, String nome, String cpf, String telefone) {
		super();
		this.numSequencial = numSequencial;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Integer getNumSequencial() {
		return numSequencial;
	}

	public void setNumSequencial(Integer numSequencial) {
		this.numSequencial = numSequencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((numSequencial == null) ? 0 : numSequencial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}

}
