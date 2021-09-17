package com.sigabem.frete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigabem.frete.dto.EnderecamentoDTO;
import com.sigabem.frete.service.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnderecamentoService {
	
	private WebClient webClientCep;
	
	public EnderecamentoDTO findCep(String cep) {
		
		Mono<EnderecamentoDTO> monoEnderecamentoDTO = this.webClientCep
				.get()
				.uri(cep + "/json/")
				.retrieve()
				.bodyToMono(EnderecamentoDTO.class);
		
		EnderecamentoDTO enderecamentoDTO = monoEnderecamentoDTO.block();
		
		if(enderecamentoDTO.getCep() == null) {
			throw new ObjectNotFoundException("Cep " + cep + " não encontrado!");
		}
		
		return enderecamentoDTO;
		
	}

}
