package com.renato.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.os.domain.Cliente;
import com.renato.os.domain.Pessoa;
import com.renato.os.dto.ClienteDTO;
import com.renato.os.repository.ClienteRepository;
import com.renato.os.repository.PessoaRepository;
import com.renato.os.service.exception.DataIntegrityViolationException;
import com.renato.os.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer numSequencial) {
		Optional<Cliente> obj = repository.findById(numSequencial);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Código: " + numSequencial + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO objDto) {
		if(findByCpf(objDto) != null) {
			throw new DataIntegrityViolationException("CPF jpa cadastrado na base de dados. ");
		}
		return repository.save(new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone()));
	}
	
	public Cliente update(Integer numSequencial, @Valid ClienteDTO objDto) {
		Cliente oldObj = findById(numSequencial);
		
		if(findByCpf(objDto) != null && findByCpf(objDto).getNumSequencial() != numSequencial) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados. ");
		}
		oldObj.setNome(objDto.getNome());
		oldObj.setCpf(objDto.getCpf());
		oldObj.setTelefone(objDto.getTelefone());
		return repository.save(oldObj);
	}
	
	public void delete(Integer numSequencial) {
		Cliente obj = findById(numSequencial);
		if(obj.getOrdensServicoList().size() > 0) {
			throw new DataIntegrityViolationException("O cliente possui Ordem de Serviço vinculada a ele. ");
		}
	}
	
	private Pessoa findByCpf(ClienteDTO objDto) {
		Pessoa obj = pessoaRepository.findByCpf(objDto.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

}
