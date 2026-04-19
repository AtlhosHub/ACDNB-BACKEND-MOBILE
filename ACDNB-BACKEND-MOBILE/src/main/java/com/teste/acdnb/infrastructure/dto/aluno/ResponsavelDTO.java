package com.teste.acdnb.infrastructure.dto.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ResponsavelDTO(
        @NotBlank(message = "Nome do responsavel nao pode ser vazio")
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome do responsavel deve conter apenas letras e espacos")
        String nome,

        @NotBlank(message = "CPF do responsavel nao pode ser vazio")
        @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$", message = "CPF do responsavel deve ser valido")
        String cpf,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Celular do responsavel deve ser valido")
        String celular,

        @Email(message = "Email do responsavel deve ser valido")
        String email,

        @NotBlank(message = "RG do responsavel nao pode ser vazio")
        String rg,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Telefone do responsavel deve ser valido")
        String telefone,

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome social do responsavel deve conter apenas letras e espacos")
        String nomeSocial,

        String genero,
        String profissao
) {
}
