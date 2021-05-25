package com.renato.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OrdemServico> ordensServicoList = new ArrayList<>();
	
	public Tecnico() {
		super();
	}

	public Tecnico(Integer numSequencial, String nome, String cpf, String telefone) {
		super(numSequencial, nome, cpf, telefone);
	}

	public List<OrdemServico> getOrdensServicoList() {
		return ordensServicoList;
	}

	public void setOrdensServicoList(List<OrdemServico> ordensServicoList) {
		this.ordensServicoList = ordensServicoList;
	}

}
