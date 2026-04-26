package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CensoRepositoryGateway implements CensoGateway {

    private final CensoRepository censoRepository;

    public CensoRepositoryGateway(CensoRepository censoRepository) {
        this.censoRepository = censoRepository;
    }

    @Override
    public void salvarCenso(Censo censo){
        censoRepository.save(CensoEntityMapper.toEntity(censo));
    }

    @Override
    public List<CensoDTO> listarMunicipios(){
        return censoRepository.findAll().stream().map(CensoEntityMapper::toDTO).toList();
    }
}
