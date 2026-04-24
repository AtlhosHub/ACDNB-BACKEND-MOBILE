package com.teste.acdnb.core.application.usecase.censo;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ImportarCensoUseCaseImpl implements ImportarCensoUseCase {

    private final CensoGateway censoGateway;

    public ImportarCensoUseCaseImpl(CensoGateway censoGateway) {
        this.censoGateway = censoGateway;
    }

    @Override
    public void execute(List<CensoDTO> censo) {
        for(CensoDTO dado : censo){
            Censo dado_censo = new Censo();

            dado_censo.setId(dado.id());
            dado_censo.setNome_distrito(dado.nome_distrito());
            dado_censo.setNum_responsaveis(dado.num_responsaveis());
            dado_censo.setNum_habitantes(dado.num_habitantes());
            dado_censo.setVar_num_habitantes(dado.num_habitantes());
            dado_censo.setRenda_media_responsavel(dado.renda_media_responsavel());
            dado_censo.setVar_renda_responsavel(dado.var_renda_responsavel());
            dado_censo.setNome_cidade(dado.nome_cidade());
            dado_censo.setDataInclusao(DataInclusao.of(LocalDateTime.now()));

            censoGateway.salvarCenso(dado_censo);
        }
    }
}
