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
//    private final AdicionarAlunoUseCase adicionarAlunoUseCase;
//    private final ListarAlunosUseCase listarAlunosUseCase;
//    private final DeletarAlunoUseCase deletarAlunoUseCase;
//    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;
//    private final AtualizarAlunoUseCase atualizarAlunoUseCase;
//    private final ListarAniversariosUseCase listarAniversariosUseCase;
//    private final QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase;
    private final ListarAlunosMensalidades listarAlunosMensalidades;

    private final MensalidadeRepository mensalidadeRepository;

    public AlunoController( ListarAlunosMensalidades listarAlunosMensalidades, MensalidadeRepository mensalidadeRepository) {
        this.listarAlunosMensalidades = listarAlunosMensalidades;
        this.mensalidadeRepository = mensalidadeRepository;
    }


    @PostMapping("/comprovantes")
    public ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> listarAlunosComComprovantes(@RequestBody ListarAlunosMensalidadeFilter filtro) {
        List<AlunoComprovanteDTO> alunosComComprovantes = listarAlunosMensalidades.execute(filtro);
        long totalItems = mensalidadeRepository.countAlunosComMensalidade(filtro.status(), filtro.nome(),LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()));

        PaginacaoResponse<AlunoComprovanteDTO> response = new PaginacaoResponse<>(alunosComComprovantes, filtro.offset(), filtro.limit(), totalItems);

        return ResponseEntity.ok(response);
    }
}
