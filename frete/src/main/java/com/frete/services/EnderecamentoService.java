package com.frete.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.frete.dto.EnderecamentoDTO;
import com.frete.services.exceptions.ResourceNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class EnderecamentoService {
	
	@Autowired
	private WebClient webClientCep;
	
	
	public EnderecamentoDTO findCep(String cep) {
		
		Mono<EnderecamentoDTO> monoEnderecamentoDTO = this.webClientCep
				.get()
				.uri(cep + "/json/")
				.retrieve()
				.bodyToMono(EnderecamentoDTO.class);
		
		EnderecamentoDTO enderecamentoDTO = monoEnderecamentoDTO.block();
		
		if(enderecamentoDTO.getCep() == null) {
			throw new ResourceNotFoundException("Cep " + cep + " não encontrado!");
		}
		
		return enderecamentoDTO;
		
	}

}