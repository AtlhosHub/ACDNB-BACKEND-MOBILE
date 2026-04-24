package com.teste.acdnb.core.application.usecase.censo;


import com.teste.acdnb.infrastructure.dto.censo.RankingCidadesDTO;

import java.util.List;

public interface RankingCidadesUseCase {
    List<RankingCidadesDTO> execute();
}
