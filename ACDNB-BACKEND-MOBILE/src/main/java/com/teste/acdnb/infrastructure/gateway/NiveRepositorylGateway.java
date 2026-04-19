package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.NivelGateway;
import com.teste.acdnb.core.domain.aluno.Nivel;
import com.teste.acdnb.infrastructure.persistence.jpa.nivel.NivelEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.nivel.NivelRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NiveRepositorylGateway implements NivelGateway {
    private final NivelRepository nivelRepository;
    private final NivelEntityMapper nivelEntityMapper;


    public NiveRepositorylGateway(NivelRepository nivelRepository, NivelEntityMapper nivelEntityMapper){
        this.nivelRepository = nivelRepository;
        this.nivelEntityMapper = nivelEntityMapper;
    }

    @Override
    public List<Nivel> buscarTodos(){
        return nivelRepository.findAll()
                .stream()
                .map(NivelEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Nivel> buscarPorId(Integer id) {
        return nivelRepository.findById(id).map(NivelEntityMapper::toDomain);
    }
}
