package com.renato.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.os.dto.OrdemServicoDTO;
import com.renato.os.service.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService service;

	@GetMapping(value = "/{numSequencial}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer numSequencial) {
		OrdemServicoDTO obj = new OrdemServicoDTO(service.findById(numSequencial));
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> list = service.findAll().stream().map(obj -> new OrdemServicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO obj) {
		obj = new OrdemServicoDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numSequencial}")
				.buildAndExpand(obj.getNumSequencial()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServicoDTO obj) {
		obj = new OrdemServicoDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}