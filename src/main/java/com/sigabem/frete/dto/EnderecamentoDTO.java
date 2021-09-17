package com.sigabem.frete.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecamentoDTO {
	
	private String cep;
    private String uf;
    private int ddd;

}
