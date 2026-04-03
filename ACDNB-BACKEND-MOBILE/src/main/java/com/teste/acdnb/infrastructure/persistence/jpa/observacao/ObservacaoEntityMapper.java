package com.teste.acdnb.infrastructure.persistence.jpa.observacao;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Observacao;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class ObservacaoEntityMapper {
    private final AlunoEntityMapper alunoEntityMapper;

    public ObservacaoEntityMapper(AlunoEntityMapper alunoEntityMapper){
        this.alunoEntityMapper = alunoEntityMapper;
    }


    public static ObservacaoEntity toEntity(Observacao observacao){
        return new ObservacaoEntity(
                observacao.getId(),
                observacao.getDescricao(),
                AlunoEntityMapper.toEntity(observacao.getAluno())
        );
    }

    public static Observacao toDomain(ObservacaoEntity observacaoEntity){
        return new Observacao(
                observacaoEntity.getId(),
                observacaoEntity.getDescricao(),
                AlunoEntityMapper.toDomain(observacaoEntity.getAluno())
        );
    }
}
