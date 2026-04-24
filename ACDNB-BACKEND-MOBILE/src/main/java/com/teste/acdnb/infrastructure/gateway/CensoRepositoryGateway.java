package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.CensoGateway;
import com.teste.acdnb.core.domain.censo.Censo;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.censo.CensoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<CensoDTO> listarDistritos(){
        return censoRepository.findAll().stream().map(CensoEntityMapper::toDTO).toList();
    }

    @Override
    public Map<String, List<CensoDTO>> censoPorCidade() {
        List<CensoEntity> censos = censoRepository.findAll();
        List<CensoDTO> censoDTOs = censos.stream().map(CensoEntityMapper::toDTO).toList();

        return censoDTOs.stream()
                .collect(Collectors.groupingBy(
                        c -> c.id().substring(0, 7)
                ));
    }
}
