package com.teste.acdnb.infrastructure.persistence.jpa.observacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "observacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa as observações do aluno no sistema")
public class ObservacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da observação", example = "1")
    private int id;

    @Schema(description = "Descrição da observação", example = "Aluno está com dificuldade no saque")
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "aluno_id")
//    @JsonIgnoreProperties("alunos")
    @Schema(description = "Aluno associado à observação")
    private AlunoEntity aluno;
}
