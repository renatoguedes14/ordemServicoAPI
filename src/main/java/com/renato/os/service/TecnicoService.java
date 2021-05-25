package com.renato.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.os.domain.Pessoa;
import com.renato.os.domain.Tecnico;
import com.renato.os.dto.TecnicoDTO;
import com.renato.os.repository.PessoaRepository;
import com.renato.os.repository.TecnicoRepository;
import com.renato.os.service.exception.DataIntegrityViolationException;
import com.renato.os.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer numSequencial) {
		Optional<Tecnico> obj = repository.findById(numSequencial);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Código: " + numSequencial + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDto) {
		if(findByCpf(objDto) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados. ");
		}
		return repository.save(new Tecnico(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone()));
	}
	
	public Tecnico update(Integer numSequencial, @Valid TecnicoDTO objDto) {
		Tecnico oldObj = findById(numSequencial);
		
		if(findByCpf(objDto) != null && findByCpf(objDto).getNumSequencial() != numSequencial) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados. ");
		}
		oldObj.setNome(objDto.getNome());
		oldObj.setCpf(objDto.getCpf());
		oldObj.setTelefone(objDto.getTelefone());
		return repository.save(oldObj);
	}
	
	public void delete(Integer numSequencial) {
		Tecnico obj = findById(numSequencial);
		if(obj.getOrdensServicoList().size() > 0) {
			throw new DataIntegrityViolationException("O técnico possui Ordem de Serviço vinculada a ele. ");
		}
	}
	
	private Pessoa findByCpf(TecnicoDTO objDto) {
		Pessoa obj = pessoaRepository.findByCpf(objDto.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

}
