package com.teste.acdnb.infrastructure.dto.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank(message = "Logradouro nao pode ser vazio")
        String logradouro,

        @NotBlank(message = "Numero nao pode ser vazio")
        String numLog,

        @NotBlank(message = "Bairro nao pode ser vazio")
        String bairro,

        @NotBlank(message = "Cidade nao pode ser vazia")
        String cidade,

        @NotBlank(message = "Estado nao pode ser vazio")
        String estado,

        @NotBlank(message = "CEP nao pode ser vazio")
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP deve ser valido")
        String cep
) {
}
