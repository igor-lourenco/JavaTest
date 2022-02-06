package com.frete.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.frete.dto.FreteInputDTO;
import com.frete.dto.FreteOutputDTO;
import com.frete.entities.Frete;

public class FreteUtils {
	
	private static final Long FRETE_ID = 1L;
	private static final BigDecimal PESO = new BigDecimal("5.00");
	private static final String CEP_ORIGEM = "01001000";
	private static final String CEP_DESTINO = "01001000";
	private static final String NOME_DESTINATARIO = "Destinatario";
	private static final BigDecimal VL_TOTAL_FRETE = new BigDecimal("2.50");
	private static final LocalDate DATA_CONSULTA = LocalDate.now();
	private static final LocalDate DATA_PREVISTA_ENTREGA = DATA_CONSULTA.plusDays(1);
	
	public static FreteInputDTO criarFakeFreteInputDTO() {
		return FreteInputDTO.builder()
				.peso(PESO)
				.cepOrigem(CEP_ORIGEM)
				.cepDestino(CEP_DESTINO)
				.nomeDestinatario(NOME_DESTINATARIO)
				.build();
	}
	
	public static FreteOutputDTO criarFakeFreteOutputDTO() {
		return FreteOutputDTO.builder()
				.id(FRETE_ID)
				.vlTotalFrete(VL_TOTAL_FRETE)
				.dataPrevistaEntrega(DATA_PREVISTA_ENTREGA)
				.cepOrigem(CEP_ORIGEM)
				.cepDestino(CEP_DESTINO)
				.build();
	}
	
	public static Frete criarFakeFreteModel() {
		return Frete.builder()
				.id(FRETE_ID)
				.peso(PESO)
				.cepOrigem(CEP_ORIGEM)
				.cepDestino(CEP_DESTINO)
				.nomeDestinatario(NOME_DESTINATARIO)
				.vlTotalFrete(VL_TOTAL_FRETE)
				.dataPrevistaEntrega(DATA_PREVISTA_ENTREGA)
				.dataConsulta(DATA_CONSULTA)
				.build();
	}

}