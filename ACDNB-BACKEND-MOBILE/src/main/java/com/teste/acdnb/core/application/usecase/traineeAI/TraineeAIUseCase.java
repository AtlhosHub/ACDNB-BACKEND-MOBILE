package com.teste.acdnb.core.application.usecase.traineeAI;

import com.teste.acdnb.infrastructure.dto.AIChatDTO;

import java.util.List;

public interface TraineeAIUseCase {
    String gerarDeTexto(String message, List<AIChatDTO.AlunoResumoDTO> students);
    String gerarDeAudio(String audioBase64, String mimeType, List<AIChatDTO.AlunoResumoDTO> students);
}