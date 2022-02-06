package com.frete.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Frete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal peso;
	
	@Column(nullable = false, length = 8)
	private String cepOrigem;
	
	@Column(nullable = false, length = 8)
	private String cepDestino;
	
	@Column(nullable = false)
	private String nomeDestinatario;
	
	@Column
	private BigDecimal vlTotalFrete;
	
	@Column
	private LocalDate dataPrevistaEntrega;
	
	@Column
	private LocalDate dataConsulta;

}