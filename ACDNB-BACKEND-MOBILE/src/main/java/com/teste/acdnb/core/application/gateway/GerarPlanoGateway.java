package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.infrastructure.dto.AIChatDTO;

import java.util.List;

public interface GerarPlanoGateway {
    String gerarPlano(String message, List<AIChatDTO.AlunoResumoDTO> students);
}