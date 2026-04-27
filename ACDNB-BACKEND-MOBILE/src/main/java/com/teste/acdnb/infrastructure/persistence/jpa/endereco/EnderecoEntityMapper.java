package com.teste.acdnb.infrastructure.persistence.jpa.endereco;

import com.teste.acdnb.core.domain.shared.valueobject.Endereco;
import com.teste.acdnb.core.domain.shared.valueobject.Cep;
import org.springframework.stereotype.Component;

@Component
public class EnderecoEntityMapper {

    public EnderecoEntityMapper() {
    }

    public static EnderecoEntity toEntity(Endereco endereco) {
        return new EnderecoEntity(
            endereco.getId(),
            endereco.getLogradouro(),
            endereco.getNumLog(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getCep().getValue(),
            null,
            null
//            AlunoMapperUtil.toEntityList(endereco.getAlunos(), new AlunoEntityMapper(null, this, null))
        );
    }

    public static Endereco toDomain(EnderecoEntity enderecoEntity) {
        return new Endereco(
            enderecoEntity.getId(),
            enderecoEntity.getLogradouro(),
            enderecoEntity.getNumLog(),
            enderecoEntity.getBairro(),
            enderecoEntity.getCidade(),
            enderecoEntity.getEstado(),
            Cep.of(enderecoEntity.getCep()),
            null,
            null
//            AlunoMapperUtil.toDomainList(enderecoEntity.getAlunos(), new AlunoEntityMapper(null, this, null))
        );
    }
}
