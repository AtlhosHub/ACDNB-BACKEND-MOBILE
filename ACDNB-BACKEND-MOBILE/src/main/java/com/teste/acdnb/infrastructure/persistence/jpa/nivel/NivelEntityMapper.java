package com.teste.acdnb.infrastructure.persistence.jpa.nivel;

import com.teste.acdnb.core.domain.aluno.Nivel;
import org.springframework.stereotype.Component;

@Component
public class NivelEntityMapper {
    public NivelEntityMapper() {
    }

    public static NivelEntity toEntity(Nivel nivel) {
        return new NivelEntity(
                nivel.getId(),
                nivel.getDescricao()
        );
    }

    public static Nivel toDomain(NivelEntity nivelEntity) {
        return new Nivel(
                nivelEntity.getId(),
                nivelEntity.getDescricao()
        );
    }
}
