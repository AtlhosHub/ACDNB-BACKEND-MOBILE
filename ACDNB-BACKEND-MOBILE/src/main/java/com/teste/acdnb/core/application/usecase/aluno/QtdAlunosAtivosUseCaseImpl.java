package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;

public class QtdAlunosAtivosUseCaseImpl implements QtdAlunosAtivosUseCase {
    private final AlunoGateway alunoGateway;

    public QtdAlunosAtivosUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Integer execute() {
        return alunoGateway.qtdAlunosAtivos();
    }
}
