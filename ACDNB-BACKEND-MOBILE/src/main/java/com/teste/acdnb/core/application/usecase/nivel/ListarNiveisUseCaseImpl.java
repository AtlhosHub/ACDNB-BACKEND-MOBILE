package com.teste.acdnb.core.application.usecase.nivel;

import com.teste.acdnb.core.application.gateway.NivelGateway;
import com.teste.acdnb.core.domain.aluno.Nivel;

import java.util.List;

public class ListarNiveisUseCaseImpl implements ListarNiveisUseCase{
    private final NivelGateway nivelGateway;

    public ListarNiveisUseCaseImpl(NivelGateway nivelGateway){
        this.nivelGateway = nivelGateway;
    }

    @Override
    public List<Nivel> execute(){
        return nivelGateway.buscarTodos();
    }
}
