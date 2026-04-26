package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;

import java.util.List;

public interface CensoGateway {
    void salvarCenso(Censo censo);

    List<CensoDTO> listarMunicipios();
}
