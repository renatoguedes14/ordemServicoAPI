package com.renato.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.os.domain.Cliente;
import com.renato.os.dto.ClienteDTO;
import com.renato.os.service.ClienteService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{numSequencial}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer numSequencial) {
		ClienteDTO objDto = new ClienteDTO(service.findById(numSequencial));
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> clientesDtoList = service.findAll().stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(clientesDtoList);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
		Cliente newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numSequencial}")
				.buildAndExpand(newObj.getNumSequencial()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{numSequencial}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer numSequencial,
			@Valid @RequestBody ClienteDTO objDto) {
		ClienteDTO newObj = new ClienteDTO(service.update(numSequencial, objDto));
		return ResponseEntity.ok().body(newObj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer numSequencial) {
		service.delete(numSequencial);
		return ResponseEntity.noContent().build();
	}

}
