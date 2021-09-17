package com.sigabem.frete.utils;

import com.sigabem.frete.dto.EnderecamentoDTO;

public class EnderecamentoUtils {
	
	private static final String CEP_SP = "01001000";
    private static final String UF_SP = "SP";
    private static final int DDD_SP = 11;
    
    private static final String CEP_FLO = "88020231";
    private static final String UF_FLO = "SC";
    private static final int DDD_FLO = 48;
    
    private static final String CEP_BLU = "89010000";
    private static final String UF_BLU = "SC";
    private static final int DDD_BLU = 47;
    
    public static EnderecamentoDTO criarFakeEnderecamentoDTOSP() {
		return EnderecamentoDTO.builder()
				.cep(CEP_SP)
				.uf(UF_SP)
				.ddd(DDD_SP)
				.build();
	}
    
    public static EnderecamentoDTO criarFakeEnderecamentoDTOFlorianopolis() {
		return EnderecamentoDTO.builder()
				.cep(CEP_FLO)
				.uf(UF_FLO)
				.ddd(DDD_FLO)
				.build();
	}
    
    public static EnderecamentoDTO criarFakeEnderecamentoDTOBlumenau() {
		return EnderecamentoDTO.builder()
				.cep(CEP_BLU)
				.uf(UF_BLU)
				.ddd(DDD_BLU)
				.build();
	}

}
