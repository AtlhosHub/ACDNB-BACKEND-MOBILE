package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCase;
import com.teste.acdnb.core.application.usecase.observacao.BuscarObservacaoPorAlunoUseCase;
import com.teste.acdnb.core.application.usecase.traineeAI.TraineeAIUseCase;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Observacao;
import com.teste.acdnb.infrastructure.dto.AIChatDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainer")
@SecurityRequirement(name = "Bearer")
@Tag(name = "TraineeController", description = "Endpoints para gerenciar os niveis dos alunos no sistema")
public class TrainerController {

    private final TraineeAIUseCase useCase;
    private final BuscarObservacaoPorAlunoUseCase buscarObservacoes;
    private final ListarAlunosUseCase listarAlunosUseCase;

    public TrainerController(
            TraineeAIUseCase useCase,
            BuscarObservacaoPorAlunoUseCase buscarObservacoes,
            ListarAlunosUseCase listarAlunosUseCase
    ) {
        this.useCase = useCase;
        this.buscarObservacoes = buscarObservacoes;
        this.listarAlunosUseCase = listarAlunosUseCase;
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<AIChatDTO.AlunoResumoDTO>> listarAlunos() {
        List<Aluno> alunos = listarAlunosUseCase.execute();

        List<AIChatDTO.AlunoResumoDTO> resumos = alunos.stream()
                .map(aluno -> {
                    List<String> obs = buscarObservacoes.execute(aluno.getId())
                            .stream()
                            .map(Observacao::getDescricao)
                            .collect(Collectors.toList());

                    String nivel = aluno.getNivel() != null
                            ? aluno.getNivel().getDescricao()
                            : null;

                    return new AIChatDTO.AlunoResumoDTO(
                            aluno.getId(),
                            aluno.getNome().getValue(),
                            nivel,
                            obs
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resumos);
    }

    @PostMapping("/plano")
    public ResponseEntity<String> generatePlan(@RequestBody AIChatDTO.GerarPlanoRequest request) {
        String plano = useCase.gerarDeTexto(request.getMessage(), request.getStudents());
        return ResponseEntity.ok(plano);
    }

    @PostMapping("/transcrever")
    public ResponseEntity<String> transcribe(@RequestBody AIChatDTO.TranscricaoRequest request) {
        String plano = useCase.gerarDeAudio(
                request.getAudioBase64(),
                request.getMimeType(),
                request.getStudents()
        );
        return ResponseEntity.ok(plano);
    }
}