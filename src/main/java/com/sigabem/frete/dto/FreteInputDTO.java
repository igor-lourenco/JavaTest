package com.sigabem.frete.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreteInputDTO {
	
	//private Long id;
	
	@NotNull(message = "O campo peso deve ser preenchido")
	private BigDecimal peso;
	
	@NotBlank(message = "O campo cepOrigem deve ser preenchido")
	@Size(min = 8, max = 8, message = "O campo cepOrigem deve ter 8 valores")
	@Pattern(regexp = "^[0-9]*$", message = "O campo cepOrigem só deve conter algarismos")
	private String cepOrigem;
	
	@NotBlank(message = "O campo cepDestino deve ser preenchido")
	@Size(min = 8, max = 8, message = "O campo cepDestino deve ter 8 valores")
	@Pattern(regexp = "^[0-9]*$", message = "O campo cepDestino só deve conter algarismos")
	private String cepDestino;
	
	@NotBlank(message = "O campo nomeDestinatario deve ser preenchido")
	private String nomeDestinatario;

}
