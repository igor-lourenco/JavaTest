package com.sigabem.frete.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sigabem.frete.model.Frete;

import static com.sigabem.frete.utils.FreteUtils.criarFakeFreteModel;

@DataJpaTest
public class FreteRepositoryTest {
	
	@Autowired
	private FreteRepository freteRepository;
	
	@Test
	void save_RetornaFretePersistida_QuandoBemSucedido() {
		Frete freteEsperado = criarFakeFreteModel();
		
		Frete freteSalvo = this.freteRepository.save(freteEsperado);
		
		freteEsperado.setId(freteSalvo.getId());
		
		assertNotNull(freteSalvo);
		assertEquals(freteEsperado, freteSalvo);
	}
	
	@Test
	void save_ReturnsFreteAtaulizado_QuandoFreteExistenteEEditado() {
		
		Frete freteSalvo = this.freteRepository.save(criarFakeFreteModel());
		Frete antigoFrete = this.guardarAntigoFrete(freteSalvo);
		Frete freteAtualizado = this.freteRepository.save(this.alterarPropriedadesFrete(freteSalvo));
		
		assertEquals(antigoFrete.getId(), freteAtualizado.getId());
		
		assertNotEquals(antigoFrete, freteAtualizado.getPeso());
		assertEquals(new BigDecimal("15"), freteAtualizado.getPeso());
		
		assertNotEquals(antigoFrete.getCepOrigem(), freteAtualizado.getCepOrigem());
		assertEquals("23932715", freteAtualizado.getCepOrigem());
		
		assertNotEquals(antigoFrete.getCepDestino(), freteAtualizado.getCepDestino());
		assertEquals("23932715", freteAtualizado.getCepDestino());
		
		assertNotEquals(antigoFrete.getNomeDestinatario(), freteAtualizado.getNomeDestinatario());
		assertEquals("Novo Destinatario", freteAtualizado.getNomeDestinatario());
		
		assertNotEquals(antigoFrete.getVlTotalFrete(), freteAtualizado.getVlTotalFrete());
		assertEquals(new BigDecimal("3.75"), freteAtualizado.getVlTotalFrete());
		
		assertNotEquals(antigoFrete.getDataPrevistaEntrega(), freteAtualizado.getDataPrevistaEntrega());
		assertEquals(LocalDate.of(2021, 3, 28), freteAtualizado.getDataPrevistaEntrega());
		
		assertNotEquals(antigoFrete.getDataConsulta(), freteAtualizado.getDataConsulta());
		assertEquals(LocalDate.of(2021, 3, 25), freteAtualizado.getDataConsulta());
	}
	
	@Test
	void findById_RetornaFrete_QuandoBemSucedido() {
		
		Frete freteSalvo = this.freteRepository.save(criarFakeFreteModel());
		
		Optional<Frete> freteRetornado = this.freteRepository.findById(freteSalvo.getId());
		
		assertEquals(true, freteRetornado.isPresent());
		assertEquals(freteSalvo, freteRetornado.get());
	}
	
	@Test
	void findById_RetornaOptionalVazio_QuandoNaoExisteId() {
		
		Frete freteSalvo = this.freteRepository.save(criarFakeFreteModel());
		
		Optional<Frete> freteRetornado = this.freteRepository.findById(freteSalvo.getId() + 100);
		
		assertEquals(false, freteRetornado.isPresent());
	}
	
	@Test
	void findAll_RetornaListaDeFretes_QuandoBemSucedido() {
		Frete primeiroFrete = criarFakeFreteModel();
		Frete segundoFrete = criarFakeFreteModel();
		
		primeiroFrete.setNomeDestinatario("Primeiro Destinario");
		segundoFrete.setNomeDestinatario("Segundo Destinario");
		
		primeiroFrete = freteRepository.save(primeiroFrete);
		segundoFrete = freteRepository.save(segundoFrete);
		
		List<Frete> rooms = this.freteRepository.findAll();
		
		assertEquals(2, rooms.size());
		assertEquals(primeiroFrete, rooms.get(0));
		assertEquals(segundoFrete, rooms.get(1));
	}
	
	@Test
	void findAll_RetornaListaVazia_QuandoNaoExisteFrete() {
		
		List<Frete> fretes = this.freteRepository.findAll();
		
		assertEquals(true, fretes.isEmpty());
	}
	
	@Test
	void delete_RetornaVoid_QuandoBemSucedido() {
		
		Frete freteSalvo = this.freteRepository.save(criarFakeFreteModel());
		
		this.freteRepository.deleteById(freteSalvo.getId());
		
		Optional<Frete> freteRetornado = this.freteRepository.findById(freteSalvo.getId());
		
		assertEquals(false, freteRetornado.isPresent());
	}
	
	private Frete guardarAntigoFrete(Frete freteAtual) {
		return Frete.builder()
				.id(freteAtual.getId())
				.peso(freteAtual.getPeso())
				.cepOrigem(freteAtual.getCepOrigem())
				.cepDestino(freteAtual.getCepDestino())
				.nomeDestinatario(freteAtual.getNomeDestinatario())
				.vlTotalFrete(freteAtual.getVlTotalFrete())
				.dataPrevistaEntrega(freteAtual.getDataPrevistaEntrega())
				.dataConsulta(freteAtual.getDataConsulta())
				.build();
	}
	
	private Frete alterarPropriedadesFrete(Frete freteAtual) {
		Frete freteParaSerAtualizado = freteAtual;
		freteParaSerAtualizado.setPeso(new BigDecimal("15"));
		freteParaSerAtualizado.setCepOrigem("23932715");;
		freteParaSerAtualizado.setCepDestino("23932715");
		freteParaSerAtualizado.setNomeDestinatario("Novo Destinatario");
		freteParaSerAtualizado.setVlTotalFrete(new BigDecimal("3.75"));
		freteParaSerAtualizado.setDataPrevistaEntrega(LocalDate.of(2021, 3, 28));
		freteParaSerAtualizado.setDataConsulta(LocalDate.of(2021, 3, 25));
		
		return freteParaSerAtualizado;
	}

}
