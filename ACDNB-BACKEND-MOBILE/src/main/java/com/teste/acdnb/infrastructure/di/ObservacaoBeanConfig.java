package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.ObservacaoGateway;
import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCase;
import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservacaoBeanConfig {
    @Bean
    public BuscarObservacaoPorAlunoUseCase buscarObservacaoPorAlunoUseCase(ObservacaoGateway observacaoGateway){
        return new BuscarObservacaoPorAlunoUseCaseImpl(observacaoGateway);
    }
}
