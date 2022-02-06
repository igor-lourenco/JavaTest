package com.frete.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frete.dto.EnderecamentoDTO;
import com.frete.dto.FreteInputDTO;
import com.frete.dto.FreteOutputDTO;
import com.frete.entities.Frete;
import com.frete.repositories.FreteRepository;
import com.frete.services.exceptions.ResourceNotFoundException;

@Service
public class FreteService {
	
	@Autowired
	private FreteRepository freteRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EnderecamentoService enderecamentoService;
	
	public FreteOutputDTO create(FreteInputDTO freteInputDTO) {
		Frete frete = modelMapper.map(freteInputDTO, Frete.class);
		frete.setDataConsulta(LocalDate.now());
		verificarEnderecamento(frete);
		return modelMapper.map(freteRepository.save(frete), FreteOutputDTO.class);
	}
	
	public FreteOutputDTO findById(Long id) {
		Optional<Frete> objFrete = freteRepository.findById(id);
		return modelMapper.map(objFrete.orElseThrow(() -> new ResourceNotFoundException("Frete não encontrado!")), FreteOutputDTO.class);
	}
	
	public List<FreteOutputDTO> findAll() {
		List<Frete> fretes = freteRepository.findAll();
		return fretes.stream().map(frete -> modelMapper.map(frete, FreteOutputDTO.class)).collect(Collectors.toList());
	}
	
	public FreteOutputDTO update(Long id, FreteInputDTO freteDTOAtualizado) {
		findById(id);
		Frete freteParaAtualizar = modelMapper.map(freteDTOAtualizado, Frete.class);
		freteParaAtualizar.setId(id);
		freteParaAtualizar.setDataConsulta(LocalDate.now());
		verificarEnderecamento(freteParaAtualizar);
		Frete freteAtualizado = freteRepository.save(freteParaAtualizar);
		return modelMapper.map(freteAtualizado, FreteOutputDTO.class);
	}
	
	public void delete(Long id) {
		findById(id);
		freteRepository.deleteById(id);
	}
	
	private void verificarEnderecamento(Frete frete) {
		EnderecamentoDTO enderecamentoOrigem = enderecamentoService.findCep(frete.getCepOrigem());
		EnderecamentoDTO enderecamentoDestino = enderecamentoService.findCep(frete.getCepDestino());
		
		int desconto = 0;
		int diasParaEntrega = 10;
		
		if(enderecamentoOrigem.getDdd() == enderecamentoDestino.getDdd()) {
			desconto = 50;
			diasParaEntrega = 1;
		}
		else if(enderecamentoOrigem.getUf().equals(enderecamentoDestino.getUf())) {
			desconto = 75;
			diasParaEntrega = 3;
		}
		
		frete.setVlTotalFrete(this.calcularValorTotal(frete.getPeso().doubleValue(), desconto));
		frete.setDataPrevistaEntrega(frete.getDataConsulta().plusDays(diasParaEntrega));
	}
	
	private BigDecimal calcularValorTotal(double peso, int desconto) {
		Double formula = peso - (peso * desconto/100);
		return BigDecimal.valueOf(formula).setScale(2, RoundingMode.DOWN);
	}

}