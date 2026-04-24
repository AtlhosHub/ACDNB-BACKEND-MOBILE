package com.teste.acdnb.core.application.usecase.traineeAI;

import com.teste.acdnb.core.application.gateway.GerarPlanoGateway;
import com.teste.acdnb.core.application.gateway.TranscricaoAudioGateway;
import com.teste.acdnb.infrastructure.dto.AIChatDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public class TraineeAIUseCaseImpl implements TraineeAIUseCase {

    private final GerarPlanoGateway planoGateway;
    private final TranscricaoAudioGateway audioGateway;

    public TraineeAIUseCaseImpl(
            GerarPlanoGateway planoGateway,
            TranscricaoAudioGateway audioGateway
    ) {
        this.planoGateway = planoGateway;
        this.audioGateway = audioGateway;
    }

    @Override
    public String gerarDeTexto(String message, List<AIChatDTO.AlunoResumoDTO> students) {
        return planoGateway.gerarPlano(message, students);
    }

    @Override
    public String gerarDeAudio(String audioBase64, String mimeType, List<AIChatDTO.AlunoResumoDTO> students) {
        String transcricao = audioGateway.transcrever(audioBase64, mimeType);
        return planoGateway.gerarPlano(transcricao, students);
    }
}