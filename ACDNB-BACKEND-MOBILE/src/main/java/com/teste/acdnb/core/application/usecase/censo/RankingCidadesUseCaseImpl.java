package com.teste.acdnb.core.application.usecase.censo;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.dto.censo.RankingCidadesDTO;

import java.util.*;
import java.util.function.BiFunction;
import static com.teste.acdnb.infrastructure.util.TextUtils.normalizarCidade;

public class RankingCidadesUseCaseImpl implements RankingCidadesUseCase {
    private final CensoGateway censoGateway;
    private final ListaEsperaGateway listaEsperaGateway;

    public RankingCidadesUseCaseImpl(CensoGateway censoGateway, ListaEsperaGateway listaEsperaGateway) {
        this.censoGateway = censoGateway;
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @Override
    public List<RankingCidadesDTO> execute() {

        Map<String, Long> interessadosPorCidade = listaEsperaGateway.interessadosPorCidade();
        List<CensoDTO> censos = censoGateway.listarMunicipios();

        if (censos.isEmpty()) return Collections.emptyList();

        // =========================
        // 1. Encontrar min e max
        // =========================
        double minRenda = censos.stream().mapToDouble(CensoDTO::renda_media_responsavel).min().orElse(0);
        double maxRenda = censos.stream().mapToDouble(CensoDTO::renda_media_responsavel).max().orElse(0);

        double minPop = censos.stream().mapToDouble(CensoDTO::num_habitantes).min().orElse(0);
        double maxPop = censos.stream().mapToDouble(CensoDTO::num_habitantes).max().orElse(0);

        double minDom = censos.stream().mapToDouble(CensoDTO::num_responsaveis).min().orElse(0);
        double maxDom = censos.stream().mapToDouble(CensoDTO::num_responsaveis).max().orElse(0);

        double minVar = censos.stream().mapToDouble(CensoDTO::var_renda_responsavel).min().orElse(0);
        double maxVar = censos.stream().mapToDouble(CensoDTO::var_renda_responsavel).max().orElse(0);

        double minInt = censos.stream()
                .mapToDouble(c -> interessadosPorCidade
                        .getOrDefault(normalizarCidade(c.nome_municipio()), 0L))
                .min().orElse(0);

        double maxInt = censos.stream()
                .mapToDouble(c -> interessadosPorCidade
                        .getOrDefault(normalizarCidade(c.nome_municipio()), 0L))
                .max().orElse(0);

        // =========================
        // 2. Função de normalização
        // =========================
        BiFunction<Double, double[], Double> normalize = (value, minMax) -> {
            double min = minMax[0];
            double max = minMax[1];
            if (max == min) return 0.0;
            return (value - min) / (max - min);
        };

        // =========================
        // 3. Calcular score
        // =========================
        List<RankingCidadesDTO> ranking = censos.stream().map(c -> {

                    String nomeCidade = normalizarCidade(c.nome_municipio());

                    long interessados = interessadosPorCidade.getOrDefault(nomeCidade, 0L);

                    double scoreRenda = normalize.apply(
                            (double) c.renda_media_responsavel(),
                            new double[]{minRenda, maxRenda}
                    );

                    double scorePop = normalize.apply(
                            (double) c.num_habitantes(),
                            new double[]{minPop, maxPop}
                    );

                    double scoreDom = normalize.apply(
                            (double) c.num_responsaveis(),
                            new double[]{minDom, maxDom}
                    );

                    // menor variância = melhor → inverter
                    double scoreEstabilidade = 1 - normalize.apply(
                            (double) c.var_renda_responsavel(),
                            new double[]{minVar, maxVar}
                    );

                    double scoreDemanda = normalize.apply(
                            (double) interessados,
                            new double[]{minInt, maxInt}
                    );

                    double scoreFinal =
                            0.35 * scoreRenda +
                                    0.15 * scorePop +
                                    0.10 * scoreDom +
                                    0.10 * scoreEstabilidade +
                                    0.30 * scoreDemanda;

                    scoreFinal *= 1000;

                    return new RankingCidadesDTO(
                            0, // rank depois
                            c.nome_municipio(),
                            scoreFinal,
                            c.latitude(),
                            c.longitude()
                    );

                }).sorted(Comparator.comparingDouble(RankingCidadesDTO::points).reversed())
                .toList();

        // =========================
        // 4. Aplicar ranking
        // =========================
        List<RankingCidadesDTO> resultadoFinal = new ArrayList<>();

        for (int i = 0; i < ranking.size(); i++) {
            RankingCidadesDTO r = ranking.get(i);

            resultadoFinal.add(new RankingCidadesDTO(
                    i + 1,
                    r.nome(),
                    r.points(),
                    r.latitude(),
                    r.longitude()
            ));
        }

        return resultadoFinal;
    }
}
