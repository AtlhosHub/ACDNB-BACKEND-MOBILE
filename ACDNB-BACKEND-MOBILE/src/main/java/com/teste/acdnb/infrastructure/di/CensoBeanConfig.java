package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCase;
import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CensoBeanConfig {
    @Bean
    public ImportarCensoUseCase importarCenso(CensoGateway censoGateway) {
        return new ImportarCensoUseCaseImpl(censoGateway);
    }
}