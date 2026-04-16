package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCase;
import com.teste.acdnb.core.domain.aluno.Observacao;
import com.teste.acdnb.infrastructure.dto.ObservacaoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/observacao")
@SecurityRequirement(name = "Bearer")
@Tag(name = "ObservacaoController", description = "Endpoints para gerenciar as observações dos alunos no sistema")
public class ObservacaoController {
    private final BuscarObservacaoPorAlunoUseCase buscarObservacaoPorAlunoUseCase;

    public ObservacaoController(BuscarObservacaoPorAlunoUseCase buscarObservacaoPorAlunoUseCase){
        this.buscarObservacaoPorAlunoUseCase = buscarObservacaoPorAlunoUseCase;
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<List<ObservacaoDTO>> listarObservacoes(@PathVariable int idAluno){
        List<Observacao> observacoes = buscarObservacaoPorAlunoUseCase.execute(idAluno);
        List<ObservacaoDTO> observacoesDTO = observacoes.stream()
                .map(obs -> new ObservacaoDTO(obs.getId(), obs.getAluno().getId(), obs.getDescricao()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(observacoesDTO);
    }
}
