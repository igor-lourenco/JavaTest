package com.sigabem.frete.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.sigabem.frete.dto.EnderecamentoDTO;
import com.sigabem.frete.dto.FreteInputDTO;
import com.sigabem.frete.dto.FreteOutputDTO;
import com.sigabem.frete.model.Frete;
import com.sigabem.frete.repository.FreteRepository;
import com.sigabem.frete.service.exceptions.ObjectNotFoundException;

import static com.sigabem.frete.utils.EnderecamentoUtils.criarFakeEnderecamentoDTOBlumenau;
import static com.sigabem.frete.utils.EnderecamentoUtils.criarFakeEnderecamentoDTOFlorianopolis;
import static com.sigabem.frete.utils.EnderecamentoUtils.criarFakeEnderecamentoDTOSP;

import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteInputDTO;
import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteOutputDTO;
import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteModel;

@ExtendWith(MockitoExtension.class)
public class FreteServiceTest {
	
	@InjectMocks
	private FreteService freteService;
	
	@Mock
	private FreteRepository freteRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private EnderecamentoService enderecamentoService;
	
	@Test
	void create_RetornaFreteOutputDTO_QuandoBemSucedido() {
		FreteInputDTO freteInputDTO = criarFakeFreteInputDTO();
		Frete freteEsperado = criarFakeFreteModel();
		FreteOutputDTO freteOutputDTO = criarFakeFreteOutputDTO();
		
		EnderecamentoDTO enderecamentoDTO = criarFakeEnderecamentoDTOSP();
		
        when(modelMapper.map(freteInputDTO, Frete.class)).thenReturn(freteEsperado);
        when(enderecamentoService.findCep(any(String.class))).thenReturn(enderecamentoDTO);
        when(freteRepository.save(any(Frete.class))).thenReturn(freteEsperado);
        when(modelMapper.map(freteEsperado, FreteOutputDTO.class)).thenReturn(freteOutputDTO);
        
        freteOutputDTO = freteService.create(freteInputDTO);
        
        assertEquals(1L, freteEsperado.getId());
        assertEquals(freteEsperado.getVlTotalFrete(), freteOutputDTO.getVlTotalFrete());
        assertEquals(freteEsperado.getDataPrevistaEntrega(), freteOutputDTO.getDataPrevistaEntrega());
        assertEquals(freteEsperado.getCepOrigem(), freteOutputDTO.getCepOrigem());
        assertEquals(freteEsperado.getCepDestino(), freteOutputDTO.getCepDestino());
	}
	
	@Test
	void findById_RetornaFreteOutputDTO_QuandoBemSucedido() {
		FreteOutputDTO freteOutputDTO = criarFakeFreteOutputDTO();
		Frete freteEsperado = criarFakeFreteModel();
		
		when(freteRepository.findById(freteEsperado.getId())).thenReturn(Optional.of(freteEsperado));
		when(modelMapper.map(freteEsperado, FreteOutputDTO.class)).thenReturn(freteOutputDTO);
		
		freteOutputDTO = freteService.findById(1L);
		
        assertEquals(freteEsperado.getVlTotalFrete(), freteOutputDTO.getVlTotalFrete());
        assertEquals(freteEsperado.getDataPrevistaEntrega(), freteOutputDTO.getDataPrevistaEntrega());
        assertEquals(freteEsperado.getCepOrigem(), freteOutputDTO.getCepOrigem());
        assertEquals(freteEsperado.getCepDestino(), freteOutputDTO.getCepDestino());
	}
	
	@Test
	void findById_ThrowsObjectNotFoundException_QuandoFreteNaoEncontrado() {
		
		assertThrows(ObjectNotFoundException.class, () -> freteService.findById(1L));
	}
	
	@Test
	void findAll_RetornaListaDeFreteOutputDTO_QuandoBemSucedido() {
		List<FreteOutputDTO> fretesOutputDTO = Collections.singletonList(criarFakeFreteOutputDTO());
		List<Frete> fretesEsperados = Collections.singletonList(criarFakeFreteModel());
		
		when(freteRepository.findAll()).thenReturn(fretesEsperados);
		when(modelMapper.map(fretesEsperados.get(0), FreteOutputDTO.class)).thenReturn(fretesOutputDTO.get(0));
		
		fretesOutputDTO = freteService.findAll();
		
		assertEquals(fretesOutputDTO.size(), 1);
		assertEquals(fretesEsperados.get(0).getId(), fretesOutputDTO.get(0).getId());
		assertEquals(fretesEsperados.get(0).getVlTotalFrete(), fretesOutputDTO.get(0).getVlTotalFrete());
		assertEquals(fretesEsperados.get(0).getDataPrevistaEntrega(), fretesOutputDTO.get(0).getDataPrevistaEntrega());
		assertEquals(fretesEsperados.get(0).getCepOrigem(), fretesOutputDTO.get(0).getCepOrigem());
		assertEquals(fretesEsperados.get(0).getCepDestino(), fretesOutputDTO.get(0).getCepDestino());
	}
	
	@Test
	void findAll_RetornaListaVazoa_QuandoNenhumaSalaExiste() {
		List<FreteOutputDTO> fretesOutputDTO;
		List<Frete> fretesEsperados = Collections.emptyList();
		
		when(freteRepository.findAll()).thenReturn(fretesEsperados);
		
		fretesOutputDTO = freteService.findAll();
		
		assertEquals(0, fretesOutputDTO.size());
	}
	
	@Test
	void update_RetornaFreteOuputDTOAtualizado_QuandoBemSucedido() {
		FreteInputDTO freteInputDTO = criarFakeFreteInputDTO();
		Frete freteEsperado = criarFakeFreteModel();
		FreteOutputDTO freteOutputDTO = criarFakeFreteOutputDTO();
		
		EnderecamentoDTO enderecamentoDTOBlumenau = criarFakeEnderecamentoDTOBlumenau();
		EnderecamentoDTO enderecamentoDTOFlorianopolis = criarFakeEnderecamentoDTOFlorianopolis();
		
		freteInputDTO.setPeso(new BigDecimal("40.00"));
		freteInputDTO.setCepDestino("88020231");
		
		freteEsperado.setPeso(new BigDecimal("40.00"));
		freteEsperado.setCepDestino("88020231");
		
		when(freteRepository.findById(1L)).thenReturn(Optional.of(freteEsperado));
		when(modelMapper.map(freteInputDTO, Frete.class)).thenReturn(freteEsperado);
		when(enderecamentoService.findCep(freteInputDTO.getCepOrigem())).thenReturn(enderecamentoDTOBlumenau);
		when(enderecamentoService.findCep(freteInputDTO.getCepDestino())).thenReturn(enderecamentoDTOFlorianopolis);
		when(freteRepository.save(any(Frete.class))).thenReturn(freteEsperado);
		when(modelMapper.map(freteEsperado, FreteOutputDTO.class)).thenReturn(freteOutputDTO);
		
		freteOutputDTO = freteService.update(1L, freteInputDTO);
		
		freteOutputDTO.setVlTotalFrete(new BigDecimal("10.00"));
		freteOutputDTO.setDataPrevistaEntrega(freteEsperado.getDataConsulta().plusDays(3));
		freteOutputDTO.setCepDestino("88020231");
		
		assertEquals(1L, freteEsperado.getId());
		assertEquals(freteEsperado.getVlTotalFrete(), freteOutputDTO.getVlTotalFrete());
		assertEquals(freteEsperado.getDataPrevistaEntrega(), freteOutputDTO.getDataPrevistaEntrega());
		assertEquals(freteEsperado.getCepOrigem(), freteOutputDTO.getCepOrigem());
		assertEquals(freteEsperado.getCepDestino(), freteOutputDTO.getCepDestino());
	}
	
	@Test
	void delete_ReturnsVoid_WhenSuccessful() {
		Frete freteEsperado = criarFakeFreteModel();
		
		when(freteRepository.findById(1L)).thenReturn(Optional.of(freteEsperado));
		doNothing().when(freteRepository).deleteById(freteEsperado.getId());
		
		freteService.delete(1L);
	}

}
