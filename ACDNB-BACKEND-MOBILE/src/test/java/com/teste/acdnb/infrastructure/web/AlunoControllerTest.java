package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.BuscarAlunoPorIdUseCase;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosMensalidades;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for AlunoController")
public class AlunoControllerTest {

    @Mock
    private BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;

    @Mock
    private ListarAlunosMensalidades listarAlunosMensalidades;

    @Mock
    private MensalidadeRepository mensalidadeRepository;

    @InjectMocks
    private AlunoController controller;

    // ==================== BUSCAR ALUNO POR ID ====================

    @Test
    @DisplayName("GET /alunos/{id} - Should return 200 and AlunoInfoDTO when found")
    void testBuscarAlunoPorIdSuccess() {
        AlunoInfoDTO alunoResponse = criarAlunoInfoDTO(1, "João Silva");

        when(buscarAlunoPorIdUseCase.execute(1)).thenReturn(alunoResponse);

        ResponseEntity<AlunoInfoDTO> response = controller.buscarAlunoPorId(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().id());
        assertEquals("João Silva", response.getBody().nome());
        verify(buscarAlunoPorIdUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("GET /alunos/{id} - Should return 404 when aluno not found")
    void testBuscarAlunoPorIdNotFound() {
        when(buscarAlunoPorIdUseCase.execute(999))
                .thenThrow(new IllegalArgumentException("Aluno não encontrado"));

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                controller.buscarAlunoPorId(999)
        );

        assertEquals("Aluno não encontrado", exception.getMessage());
        verify(buscarAlunoPorIdUseCase, times(1)).execute(999);
    }

    // ==================== LISTAR ALUNOS COM COMPROVANTES ====================

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return 200 and paginated comprovantes")
    void testListarAlunosComComprovantesSuccess() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(
                null, null, null, "2025-01-01", "2025-12-31", 0, 10
        );
        AlunoComprovanteDTO dto1 = criarAlunoComprovanteDTO(1, "João Silva");
        AlunoComprovanteDTO dto2 = criarAlunoComprovanteDTO(2, "Maria Santos");

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of(dto1, dto2));
        when(mensalidadeRepository.countAlunosComMensalidade(
                filtro.status(), filtro.nome(),
                LocalDate.parse(filtro.dataEnvioFrom()),
                LocalDate.parse(filtro.dataEnvioTo())
        )).thenReturn(2L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response =
                controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().content().size());
        verify(listarAlunosMensalidades, times(1)).execute(filtro);
    }

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return empty list when no comprovantes found")
    void testListarAlunosComComprovantesEmpty() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(
                null, null, null, "2025-01-01", "2025-12-31", 0, 10
        );

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of());
        when(mensalidadeRepository.countAlunosComMensalidade(
                filtro.status(), filtro.nome(),
                LocalDate.parse(filtro.dataEnvioFrom()),
                LocalDate.parse(filtro.dataEnvioTo())
        )).thenReturn(0L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response =
                controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().content().isEmpty());
        verify(listarAlunosMensalidades, times(1)).execute(filtro);
    }

    // ==================== HELPERS ====================

    private AlunoInfoDTO criarAlunoInfoDTO(int id, String nome) {
        return new AlunoInfoDTO(
                id,       // int id
                nome,     // String nome
                null,     // String email
                null,     // LocalDate dataNascimento
                null,     // String cpf
                null,     // String rg
                null,     // String nomeSocial
                null,     // String genero
                null,     // String celular
                null,     // String nacionalidade
                null,     // String naturalidade
                null,     // String telefone
                null,     // String profissao
                true,     // boolean ativo
                true,     // boolean temAtestado
                null,     // String deficiencia
                true,     // boolean autorizado
                null,     // LocalDateTime dataInclusao
                null,     // Endereco endereco
                null,     // List<Responsavel> responsaveis
                null      // Nivel nivel
        );
    }

    private AlunoComprovanteDTO criarAlunoComprovanteDTO(int id, String nome) {
        return new AlunoComprovanteDTO(
                1, id, nome,
                true, null, null,
                "PAGO", "CARTAO",
                null, false, false
        );
    }
}