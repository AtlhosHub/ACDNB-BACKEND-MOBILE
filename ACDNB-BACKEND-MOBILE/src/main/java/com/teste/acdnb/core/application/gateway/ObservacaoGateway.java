package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.aluno.Observacao;

import java.util.List;

public interface ObservacaoGateway {
    List<Observacao> buscarObservacaoPorAluno(int idAluno);
}
