package com.frete.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreteOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	
	private BigDecimal vlTotalFrete;
	
	private LocalDate dataPrevistaEntrega;
	
	private String cepOrigem;
	
	private String cepDestino;

}