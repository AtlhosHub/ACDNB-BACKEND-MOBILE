package com.teste.acdnb.infrastructure.dto;

import com.teste.acdnb.core.domain.shared.valueobject.Endereco;

import java.time.LocalDate;

public record ListaEsperaDTO(
    String nome,
    String email,
    String dataInteresse,
    String celular,
    String nomeSocial,
    String genero,
    LocalDate dataNascimento,
    String telefone,
    String dataInclusao,
    Integer usuarioInclusao,
    Integer horarioPrefId,
    Endereco endereco
) {
}
