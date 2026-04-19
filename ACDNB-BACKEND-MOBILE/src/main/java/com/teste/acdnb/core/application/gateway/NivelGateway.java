package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.aluno.Nivel;

import java.util.List;
import java.util.Optional;

public interface NivelGateway {
    List<Nivel> buscarTodos();
    Optional<Nivel> buscarPorId(Integer id);
}
