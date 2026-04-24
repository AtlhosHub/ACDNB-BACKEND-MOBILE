package com.teste.acdnb.core.application.usecase.censo;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.dto.censo.CidadeScoreDTO;
import com.teste.acdnb.infrastructure.dto.censo.RankingCidadesDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingCidadesUseCaseImpl implements RankingCidadesUseCase {
    private final CensoGateway censoGateway;
    private final ListaEsperaGateway listaEsperaGateway;

    public RankingCidadesUseCaseImpl(CensoGateway censoGateway, ListaEsperaGateway listaEsperaGateway) {
        this.censoGateway = censoGateway;
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @Override
    public List<RankingCidadesDTO> execute() {
        // temp
        RankingCidadesDTO ranking = new RankingCidadesDTO("", 0.0, "");
        return List.of(ranking);
    }
//        List<CensoDTO> distritos = censoGateway.listarDistritos();
//        List<ListaEspera> interessados = listaEsperaGateway.listarTodos();
//
//        Map<String, Long> interessadosPorCidade = listaEsperaGateway.interessadosPorCidade();
//        Map<String, List<CensoDTO>> censoPorCidade = censoGateway.censoPorCidade();
//
//        List<CidadeScoreDTO> cidades = new ArrayList<>();
//
//        for (String cidadeId : censoPorCidade.keySet()) {
//
//            List<CensoEntity> distritos = censoPorCidade.get(cidadeId);
//
//            double rendaMedia = distritos.stream()
//                    .mapToDouble(CensoEntity::getRendaMediaResponsavel)
//                    .average().orElse(0);
//
//            double populacao = distritos.stream()
//                    .mapToDouble(CensoEntity::getNumHabitantes)
//                    .sum();
//
//            double variacaoRenda = distritos.stream()
//                    .mapToDouble(CensoEntity::getVarRendaResponsavel)
//                    .average().orElse(0);
//
//            long interessados = interessadosPorCidade.getOrDefault(cidadeId, 0L);
//
//            cidades.add(new CidadeScoreDTO(
//                    cidadeId,
//                    rendaMedia,
//                    populacao,
//                    variacaoRenda,
//                    interessados
//            ));
//        }
//
//    }
}
