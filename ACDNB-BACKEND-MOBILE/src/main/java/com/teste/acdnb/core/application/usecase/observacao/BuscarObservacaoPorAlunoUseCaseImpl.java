package com.teste.acdnb.core.application.usecase.observacao;

import com.teste.acdnb.core.application.gateway.ObservacaoGateway;
import com.teste.acdnb.core.domain.aluno.Observacao;

import java.util.List;

public class BuscarObservacaoPorAlunoUseCaseImpl implements BuscarObservacaoPorAlunoUseCase{
    private final ObservacaoGateway observacaoGateway;

    public BuscarObservacaoPorAlunoUseCaseImpl(ObservacaoGateway observacaoGateway){
        this.observacaoGateway = observacaoGateway;
    }

    @Override
    public List<Observacao> execute(int idAluno){
        return observacaoGateway.buscarObservacaoPorAluno(idAluno);
    }
}
