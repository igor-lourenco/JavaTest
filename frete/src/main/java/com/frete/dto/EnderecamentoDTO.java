package com.frete.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cep;
    private String uf;
    private int ddd;

}