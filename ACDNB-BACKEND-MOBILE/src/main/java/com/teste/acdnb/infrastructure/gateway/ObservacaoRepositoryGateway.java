package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.ObservacaoGateway;
import com.teste.acdnb.core.domain.aluno.Observacao;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.AlunoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.observacao.ObservacaoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.observacao.ObservacaoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObservacaoRepositoryGateway implements ObservacaoGateway {
    private final ObservacaoRepository observacaoRepository;
    private final AlunoRepository alunoRepository;

    public ObservacaoEntityMapper observacaoEntityMapper;

    public ObservacaoRepositoryGateway(ObservacaoRepository observacaoRepository, AlunoRepository alunoRepository, ObservacaoEntityMapper observacaoEntityMapper){
        this.observacaoRepository = observacaoRepository;
        this.observacaoEntityMapper = observacaoEntityMapper;
        this.alunoRepository =  alunoRepository;
    }

    @Override
    public List<Observacao> buscarObservacaoPorAluno(int idAluno){
        return observacaoRepository.findByAlunoId(idAluno).stream().map(ObservacaoEntityMapper::toDomain).toList();
    }
}
