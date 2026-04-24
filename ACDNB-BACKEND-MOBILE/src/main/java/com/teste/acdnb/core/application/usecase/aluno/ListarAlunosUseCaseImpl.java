package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunosUseCaseImpl implements ListarAlunosUseCase {

    private final AlunoGateway alunoGateway;

    public ListarAlunosUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public List<Aluno> execute() {
        return alunoGateway.listarAlunos();
    }
}