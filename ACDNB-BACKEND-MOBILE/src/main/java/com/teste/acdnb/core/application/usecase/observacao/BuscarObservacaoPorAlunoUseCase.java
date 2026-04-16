package com.teste.acdnb.core.application.usecase.observacao;

import com.teste.acdnb.core.domain.aluno.Observacao;

import java.util.List;

public interface BuscarObservacaoPorAlunoUseCase {
    List<Observacao> execute(int idAluno);
}
