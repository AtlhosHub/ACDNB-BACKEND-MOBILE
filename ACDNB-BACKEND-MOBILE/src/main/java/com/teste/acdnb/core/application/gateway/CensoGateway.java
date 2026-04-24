package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoEntity;

import java.util.List;
import java.util.Map;

public interface CensoGateway {
    void salvarCenso(Censo censo);

    List<CensoDTO> listarDistritos();

    Map<String, List<CensoDTO>> censoPorCidade();
}
