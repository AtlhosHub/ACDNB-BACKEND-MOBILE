package com.teste.acdnb.core.application.usecase.censo;

import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;

import java.util.List;

public interface ImportarCensoUseCase {
    void execute(List<CensoDTO> censoEntity);
}
