package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCase;
import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCaseImpl;
import com.teste.acdnb.core.application.usecase.censo.RankingCidadesUseCase;
import com.teste.acdnb.core.application.usecase.censo.RankingCidadesUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CensoBeanConfig {
    @Bean
    public ImportarCensoUseCase importarCenso(CensoGateway censoGateway) {
        return new ImportarCensoUseCaseImpl(censoGateway);
    }

    @Bean
    public RankingCidadesUseCase rankingCidades(CensoGateway censoGateway, ListaEsperaGateway listaEsperaGateway) {
        return new RankingCidadesUseCaseImpl(censoGateway, listaEsperaGateway);
    }
}