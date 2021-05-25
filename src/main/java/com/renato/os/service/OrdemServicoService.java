package com.renato.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.os.domain.Cliente;
import com.renato.os.domain.OrdemServico;
import com.renato.os.domain.Tecnico;
import com.renato.os.domain.enums.Prioridade;
import com.renato.os.domain.enums.Status;
import com.renato.os.dto.OrdemServicoDTO;
import com.renato.os.repository.OrdemServicoRepository;
import com.renato.os.service.exception.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;

	public OrdemServico findById(Integer numSequencial) {
		Optional<OrdemServico> obj = repository.findById(numSequencial);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Ordem de Serviço não encontrada. "));
	}

	public List<OrdemServico> findAll() {
		return repository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO obj) {
		return fromDto(obj);
	}
	
	public OrdemServico update(@Valid OrdemServicoDTO obj) {
		findById(obj.getNumSequencial());
		return fromDto(obj);
	}
	
	private OrdemServico fromDto(OrdemServicoDTO obj) {
		OrdemServico newObj = new OrdemServico();
		newObj.setNumSequencial(obj.getNumSequencial());
		newObj.setObservacao(obj.getObservacao());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tecnico);
		newObj.setCliente(cliente);
		
		if(newObj.getStatus().getCodigo().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}
}