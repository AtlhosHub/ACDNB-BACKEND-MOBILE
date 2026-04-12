package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.aluno.valueobject.Cep;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.ResponsavelDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdicionarAlunoUseCaseImpl implements AdicionarAlunoUseCase {

    private final AlunoGateway alunoGateway;

    public AdicionarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(AlunoDTO alunoDTO) {
        // Verificar duplicidade de email, CPF e RG
        if (alunoGateway.existsByEmailIgnoreCaseOrCpfOrRg(alunoDTO.email(), alunoDTO.cpf(), alunoDTO.rg())) {
            throw new DataConflictException("Email, CPF ou RG já cadastrados no sistema");
        }

        // Processar endereço (reutilizar se já existir)
        Endereco enderecoDomain = new Endereco(
            0,
            alunoDTO.endereco().logradouro(),
            alunoDTO.endereco().numLog(),
            alunoDTO.endereco().bairro(),
            alunoDTO.endereco().cidade(),
            alunoDTO.endereco().estado(),
            Cep.of(alunoDTO.endereco().cep()),
            new ArrayList<>()
        );

        Optional<Endereco> enderecoExistente = alunoGateway.findEndereco(enderecoDomain);
        Endereco endereco = enderecoExistente.orElseGet(() -> alunoGateway.saveEndereco(enderecoDomain));

        // Processar responsáveis (se aluno for menor de idade)
        List<Responsavel> responsaveisProcessados = new ArrayList<>();
        if (alunoDTO.responsaveis() != null && !alunoDTO.responsaveis().isEmpty()) {
            for (ResponsavelDTO respDTO : alunoDTO.responsaveis()) {
                Responsavel responsavelDomain = new Responsavel(
                    0,
                    Nome.of(respDTO.nome()),
                    Cpf.of(respDTO.cpf()),
                    Celular.of(respDTO.celular()),
                    respDTO.email() != null ? Email.of(respDTO.email()) : null,
                    respDTO.rg(),
                    Telefone.of(respDTO.telefone()),
                    NomeSocial.of(respDTO.nomeSocial(), respDTO.nome()),
                    respDTO.genero(),
                    respDTO.profissao(),
                    new ArrayList<>()
                );

                Optional<Responsavel> respExistente = alunoGateway.findResponsavelPorCpf(respDTO.cpf());
                Responsavel responsavel = respExistente.orElseGet(() -> alunoGateway.saveResponsavel(responsavelDomain));
                responsaveisProcessados.add(responsavel);
            }
        }

        // Montar objeto domínio Aluno com construtor completo
        Aluno aluno = new Aluno(
            0, // id será gerado pelo banco
            Nome.of(alunoDTO.nome()),
            Email.of(alunoDTO.email(), alunoDTO.dataNascimento() != null),
            DataNascimento.of(alunoDTO.dataNascimento()),
            Cpf.of(alunoDTO.cpf()),
            alunoDTO.rg(),
            NomeSocial.of(alunoDTO.nomeSocial(), alunoDTO.nome()),
            alunoDTO.genero(),
            Celular.of(alunoDTO.celular()),
            Telefone.of(alunoDTO.telefone()),
            alunoDTO.nacionalidade(),
            alunoDTO.naturalidade(),
            alunoDTO.profissao(),
            alunoDTO.deficiencia(),
            alunoDTO.ativo(),
            alunoDTO.atestado(),
            alunoDTO.autorizado(),
            DataInclusao.of(alunoDTO.dataInclusao() != null ? alunoDTO.dataInclusao() : LocalDateTime.now()),
            endereco,
            responsaveisProcessados,
            new ArrayList<>()
        );

        return alunoGateway.salvarAluno(aluno);
    }
}
