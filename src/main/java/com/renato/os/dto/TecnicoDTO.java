package com.renato.os.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.renato.os.domain.Tecnico;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numSequencial;
	@NotEmpty(message = "É necessário preencher o campo 'Nome'. ")
	private String nome;
	@CPF
	@NotEmpty(message = "É necessário preencher o campo 'CPF'. ")
	private String cpf;
	@NotEmpty(message = "É necessário preencher o campo 'Telefone'. ")
	private String telefone;

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.numSequencial = obj.getNumSequencial();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
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

}
