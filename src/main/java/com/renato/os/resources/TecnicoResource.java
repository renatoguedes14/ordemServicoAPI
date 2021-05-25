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

import com.renato.os.domain.Tecnico;
import com.renato.os.dto.TecnicoDTO;
import com.renato.os.service.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{numSequencial}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer numSequencial) {
		TecnicoDTO objDto = new TecnicoDTO(service.findById(numSequencial));
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<TecnicoDTO> tecnicosDtoList = service.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(tecnicosDtoList);
	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numSequencial}")
				.buildAndExpand(newObj.getNumSequencial()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{numSequencial}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer numSequencial, @Valid @RequestBody TecnicoDTO objDto) {
		TecnicoDTO newObj = new TecnicoDTO(service.update(numSequencial, objDto));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer numSequencial) {
		service.delete(numSequencial);
		return ResponseEntity.noContent().build();
	}
}
