package com.sigabem.frete.controller;

import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteInputDTO;
import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteOutputDTO;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.sigabem.frete.dto.FreteInputDTO;
import com.sigabem.frete.dto.FreteOutputDTO;
import com.sigabem.frete.service.EnderecamentoService;
import com.sigabem.frete.service.FreteService;

import io.restassured.http.ContentType;

@WebMvcTest
public class FreteControllerTest {
	
	@Autowired
	private FreteController freteController;
	
	@MockBean
	private FreteService freteService;
	
	@MockBean
	private EnderecamentoService enderecamentoService;
	
	private final String PATH = "http://localhost:8080/v1/frete";
	
	@BeforeEach
	public void setUp() {
		standaloneSetup(this.freteController);
	}
	
	@Test
	void create_RetornaCreated_QuandoBemSucedido() {
		FreteInputDTO freteInputDTOEsperado = criarFakeFreteInputDTO();
		FreteOutputDTO freteOutputDTOEsperado = criarFakeFreteOutputDTO();
		
		when(freteService.create(any(FreteInputDTO.class))).thenReturn(freteOutputDTOEsperado);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(freteInputDTOEsperado)
		.when()
			.post(PATH)
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void findById_ReturnsRoomDTO_WhenSuccessful() {
		FreteOutputDTO freteOutputDTOEsperado = criarFakeFreteOutputDTO();
		
		when(freteService.findById(any(Long.class))).thenReturn(freteOutputDTOEsperado);
		
		given().log().all()
			.contentType(ContentType.JSON)
		.when()
			.get(PATH + "/{id}", freteOutputDTOEsperado.getId())
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("vlTotalFrete", is(freteOutputDTOEsperado.getVlTotalFrete().floatValue()))
			.body("dataPrevistaEntrega[0]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getYear()))
			.body("dataPrevistaEntrega[1]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getMonthValue()))
			.body("dataPrevistaEntrega[2]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getDayOfMonth()))
			.body("cepOrigem", is(freteOutputDTOEsperado.getCepOrigem()))
			.body("cepDestino", is(freteOutputDTOEsperado.getCepDestino()));
	}
	
	@Test
	void findAll_RetornaListaDeFreteOutputDTO_QuandoBemSucedido() {
		FreteOutputDTO primeiroFreteOutputDTOEsperado = criarFakeFreteOutputDTO();
		primeiroFreteOutputDTOEsperado.setId(3L);
		
		FreteOutputDTO segundoFreteOutputDTOEsperado = criarFakeFreteOutputDTO();
		segundoFreteOutputDTOEsperado.setId(4L);
		segundoFreteOutputDTOEsperado.setVlTotalFrete(new BigDecimal("14.5"));
		segundoFreteOutputDTOEsperado.setCepOrigem("88020231");
		segundoFreteOutputDTOEsperado.setCepDestino("01001000");
		
		List<FreteOutputDTO>fretesOutputDTO = new ArrayList<>();
		fretesOutputDTO.add(primeiroFreteOutputDTOEsperado);
		fretesOutputDTO.add(segundoFreteOutputDTOEsperado);
		
		when(freteService.findAll()).thenReturn(fretesOutputDTO);
		
		given().log().all()
			.contentType(ContentType.JSON)
		.when()
			.get(PATH)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("[0].vlTotalFrete", is(primeiroFreteOutputDTOEsperado.getVlTotalFrete().floatValue()))
			.body("[0].dataPrevistaEntrega[0]", is(primeiroFreteOutputDTOEsperado.getDataPrevistaEntrega().getYear()))
			.body("[0].dataPrevistaEntrega[1]", is(primeiroFreteOutputDTOEsperado.getDataPrevistaEntrega().getMonthValue()))
			.body("[0].dataPrevistaEntrega[2]", is(primeiroFreteOutputDTOEsperado.getDataPrevistaEntrega().getDayOfMonth()))
			.body("[0].cepOrigem", is(primeiroFreteOutputDTOEsperado.getCepOrigem()))
			.body("[0].cepDestino", is(primeiroFreteOutputDTOEsperado.getCepDestino()))
			
			.body("[1].vlTotalFrete", is(segundoFreteOutputDTOEsperado.getVlTotalFrete().floatValue()))
			.body("[1].dataPrevistaEntrega[0]", is(segundoFreteOutputDTOEsperado.getDataPrevistaEntrega().getYear()))
			.body("[1].dataPrevistaEntrega[1]", is(segundoFreteOutputDTOEsperado.getDataPrevistaEntrega().getMonthValue()))
			.body("[1].dataPrevistaEntrega[2]", is(segundoFreteOutputDTOEsperado.getDataPrevistaEntrega().getDayOfMonth()))
			.body("[1].cepOrigem", is(segundoFreteOutputDTOEsperado.getCepOrigem()))
			.body("[1].cepDestino", is(segundoFreteOutputDTOEsperado.getCepDestino()));
	}
	
	@Test
	void update_RetornaFreteOutputDTOAtualizado_QuandoBemSucedido() {
		FreteInputDTO freteInputDTOAtualizado = criarFakeFreteInputDTO();
		FreteOutputDTO freteOutputDTOEsperado = criarFakeFreteOutputDTO();
		freteOutputDTOEsperado.setCepOrigem("28950000");
		
		when(freteService.update(any(Long.class), any(FreteInputDTO.class))).thenReturn(freteOutputDTOEsperado);
		
		freteInputDTOAtualizado.setCepOrigem("28950000");
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(freteInputDTOAtualizado)
		.when()
			.put(PATH + "/{id}", 5)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("vlTotalFrete", is(freteOutputDTOEsperado.getVlTotalFrete().floatValue()))
			.body("dataPrevistaEntrega[0]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getYear()))
			.body("dataPrevistaEntrega[1]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getMonthValue()))
			.body("dataPrevistaEntrega[2]", is(freteOutputDTOEsperado.getDataPrevistaEntrega().getDayOfMonth()))
			.body("cepOrigem", is(freteOutputDTOEsperado.getCepOrigem()))
			.body("cepDestino", is(freteOutputDTOEsperado.getCepDestino()));;
	}
	
	@Test
	void delete_RetornaNoContent_QuandoBemSucedido() {
		
		doNothing().when(freteService).delete(any(Long.class));
		
		given().log().all()
			.contentType(ContentType.JSON)
		.when()
			.delete(PATH + "/{id}", 1)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}

}
