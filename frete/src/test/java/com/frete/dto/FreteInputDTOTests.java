package com.frete.dto;


import static com.frete.utils.FreteUtils.criarFakeFreteInputDTO;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FreteInputDTOTests {

	
	private static final String PESO_ESTA_NULO = "O campo peso deve ser preenchido";
	
	private static final String CEP_ORIGEM_ESTA_VAZIO = "O campo cepOrigem deve ser preenchido";
	
	private static final String CEP_ORIGEM_TAMANHO_INCORRETO = "O campo cepOrigem deve ter 8 valores";
	
	private static final String CEP_ORIGEM_TEM_CARACTERES_NAO_NUMERICOS = "O campo cepOrigem só deve conter algarismos";
	
	private static final String CEP_DESTINO_ESTA_VAZIO = "O campo cepDestino deve ser preenchido";
	
	private static final String CEP_DESTINO_TAMANHO_INCORRETO = "O campo cepDestino deve ter 8 valores";
	
	private static final String CEP_DESTINO_TEM_CARACTERES_NAO_NUMERICOS = "O campo cepDestino só deve conter algarismos";
	
	private static final String NOME_DESTINATARIO_ESTA_VAZIO = "O campo nomeDestinatario deve ser preenchido";
	
	
	private Validator validador;
	
	
	@BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validador = factory.getValidator();
    }
	
	@Test
    void teste_SemViolacoes_QuandoBemSucedido() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();

		Set<ConstraintViolation<FreteInputDTO>> violacoes = validador.validate(freteInputDTO);
		
		assertTrue(violacoes.isEmpty());
    }
	
	@Test
    void teste_ExisteViolacao_QuandoPesoEstaNulo() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setPeso(null);

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(PESO_ESTA_NULO)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepOrigemEstaVazio() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepOrigem("   ");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_ORIGEM_ESTA_VAZIO)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepOrigemEstaComTamanhoIncorreto() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepOrigem("880202310");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_ORIGEM_TAMANHO_INCORRETO)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepOrigemTemCaractereNaoNumerico() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepOrigem("8802-031");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_ORIGEM_TEM_CARACTERES_NAO_NUMERICOS)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepDestinoEstaVazio() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepDestino("   ");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_DESTINO_ESTA_VAZIO)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepDestinoEstaComTamanhoIncorreto() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepDestino("880202310");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_DESTINO_TAMANHO_INCORRETO)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoCepDestinoTemCaractereNaoNumerico() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setCepDestino("8802-031");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(CEP_DESTINO_TEM_CARACTERES_NAO_NUMERICOS)));
    }
	
	@Test
    void teste_ExisteViolacao_QuandoNomeDestinatarioEstaVazio() {
		FreteInputDTO freteInputDTO =  criarFakeFreteInputDTO();
		freteInputDTO.setNomeDestinatario(" ");

		Set<ConstraintViolation<FreteInputDTO>> violations = validador.validate(freteInputDTO);
				
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals(NOME_DESTINATARIO_ESTA_VAZIO)));
    }
}