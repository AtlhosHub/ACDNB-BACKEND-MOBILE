package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.*;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoAniversarioDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "Bearer")
@Tag(name = "AlunoController", description = "Endpoints para gerenciar os alunos no sistema")
public class AlunoController {
    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;
    private final ListarAniversariosUseCase listarAniversariosUseCase;
    private final QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase;
    private final ListarAlunosMensalidades listarAlunosMensalidades;
    private final MensalidadeRepository mensalidadeRepository;

    public AlunoController(QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase, ListarAniversariosUseCase listarAniversariosUseCase, ListarAlunosMensalidades listarAlunosMensalidades, MensalidadeRepository mensalidadeRepository, BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase) {
        this.listarAlunosMensalidades = listarAlunosMensalidades;
        this.mensalidadeRepository = mensalidadeRepository;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
        this.listarAniversariosUseCase = listarAniversariosUseCase;
        this.qtdAlunosAtivosUseCase = qtdAlunosAtivosUseCase;
    }

    @GetMapping("/totalAtivo")
    public ResponseEntity<Integer> buscarTotalAtivo() {
        Integer qtdAlunosAtivo = qtdAlunosAtivosUseCase.execute();
        return ResponseEntity.status(201).body(qtdAlunosAtivo);
    }

    @PostMapping("/comprovantes")
    public ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> listarAlunosComComprovantes(@RequestBody ListarAlunosMensalidadeFilter filtro) {
        List<AlunoComprovanteDTO> alunosComComprovantes = listarAlunosMensalidades.execute(filtro);
        long totalItems = mensalidadeRepository.countAlunosComMensalidade(filtro.status(), filtro.nome(),LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()));

        PaginacaoResponse<AlunoComprovanteDTO> response = new PaginacaoResponse<>(alunosComComprovantes, filtro.offset(), filtro.limit(), totalItems);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoInfoDTO> buscarAlunoPorId(@PathVariable int id){
        AlunoInfoDTO aluno = buscarAlunoPorIdUseCase.execute(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/aniversariantes")
    public ResponseEntity<List<AlunoAniversarioDTO>> listarAniversarios() {
        List<AlunoAniversarioDTO> aniversariantes = listarAniversariosUseCase.execute();
        return aniversariantes.isEmpty() ? ResponseEntity.ok(List.of()) : ResponseEntity.ok(aniversariantes);
    }
}
