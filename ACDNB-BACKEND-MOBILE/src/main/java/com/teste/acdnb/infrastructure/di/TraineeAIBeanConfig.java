package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.GerarPlanoGateway;
import com.teste.acdnb.core.application.gateway.ObservacaoGateway;
import com.teste.acdnb.core.application.gateway.TranscricaoAudioGateway;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCase;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCaseImpl;
import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCase;
import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCaseImpl;
import com.teste.acdnb.core.application.usecase.traineeAI.TraineeAIUseCase;
import com.teste.acdnb.core.application.usecase.traineeAI.TraineeAIUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TraineeAIBeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ListarAlunosUseCase listarAlunosUseCase(AlunoGateway alunoGateway) {
        return new ListarAlunosUseCaseImpl(alunoGateway);
    }

    @Bean
    public TraineeAIUseCase traineeAIUseCase(GerarPlanoGateway gerarPlanoGateway, TranscricaoAudioGateway transcricaoAudioGateway) {
        return new TraineeAIUseCaseImpl(gerarPlanoGateway, transcricaoAudioGateway);
    }
}