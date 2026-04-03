package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.NivelGateway;
import com.teste.acdnb.core.application.usecase.nivel.ListarNiveisUseCase;
import com.teste.acdnb.core.application.usecase.nivel.ListarNiveisUseCaseImpl;
import com.teste.acdnb.core.domain.aluno.Nivel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Configuration
public class NivelBeanConfig {
    @Bean
    public ListarNiveisUseCase listarNiveisUseCase(NivelGateway nivelGateway){
        return new ListarNiveisUseCaseImpl(nivelGateway);
    }

}
