package com.frete.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.frete.dto.FreteInputDTO;
import com.frete.dto.FreteOutputDTO;
import com.frete.services.FreteService;

@RestController
@RequestMapping("/frete")
public class FreteResource {
	
	@Autowired
	private FreteService freteService;
	
	@PostMapping
	public ResponseEntity<FreteOutputDTO> create(@Valid @RequestBody FreteInputDTO freteInputDTO) {
		FreteOutputDTO freteOutputDTO = freteService.create(freteInputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(freteOutputDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(freteOutputDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FreteOutputDTO> findById(@PathVariable Long id) {
		FreteOutputDTO freteOutputDTO = freteService.findById(id);
		return ResponseEntity.ok().body(freteOutputDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<FreteOutputDTO>> findAll() {
		return ResponseEntity.ok().body(freteService.findAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FreteOutputDTO> update(@PathVariable Long id, @Valid @RequestBody FreteInputDTO freteDTOAtualizado) {
		FreteOutputDTO freteOutputDTO = freteService.update(id, freteDTOAtualizado);
		return ResponseEntity.ok().body(freteOutputDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		freteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}