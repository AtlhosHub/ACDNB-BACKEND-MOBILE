package com.teste.acdnb.infrastructure.dto.aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoDTO(
        @NotBlank(message = "Nome do aluno nao pode ser vazio")
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome deve conter apenas letras e espacos")
        String nome,

        @Email(message = "Email deve ser valido")
        String email,

        @NotNull(message = "Data de nascimento nao pode ser nula")
        @Past(message = "Data de nascimento deve ser anterior a data atual")
        LocalDate dataNascimento,

        @NotBlank(message = "CPF nao pode ser vazio")
        @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$", message = "CPF deve ser valido")
        String cpf,

        @NotBlank(message = "RG nao pode ser vazio")
        String rg,

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome social deve conter apenas letras e espacos")
        String nomeSocial,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Celular deve ser um numero valido")
        String celular,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Telefone deve ser um numero valido")
        String telefone,

        String genero,
        String nacionalidade,
        String naturalidade,
        String profissao,
        String deficiencia,
        boolean ativo,
        boolean atestado,
        boolean autorizado,
        LocalDateTime dataInclusao,

        @Valid
        @NotNull(message = "Endereco nao pode ser nulo")
        EnderecoDTO endereco,

        @Valid
        List<ResponsavelDTO> responsaveis
) {
}
