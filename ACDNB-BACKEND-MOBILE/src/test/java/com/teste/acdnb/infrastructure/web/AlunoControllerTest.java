package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCase;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosMensalidades;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.shared.valueobject.Cpf;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.core.domain.shared.valueobject.DataNascimento;
import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.EnderecoDTO;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for AlunoController")
class AlunoControllerTest {

    @Mock
    private AdicionarAlunoUseCase adicionarAlunoUseCase;

    @Mock
    private ListarAlunosMensalidades listarAlunosMensalidades;

    @Mock
    private MensalidadeRepository mensalidadeRepository;

    @InjectMocks
    private AlunoController controller;

    @Test
    @DisplayName("POST /alunos - Should return 201 and Aluno when created successfully")
    void testAdicionarAlunoSuccess() {
        AlunoDTO alunoDTO = criarAlunoDTO();
        Aluno alunoResponse = criarAluno(1, "Joao Silva", "joao.silva@example.com", "123456789", "58165708031");

        when(adicionarAlunoUseCase.execute(alunoDTO)).thenReturn(alunoResponse);

        ResponseEntity<Aluno> response = controller.adicionarAluno(alunoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(alunoResponse.getId(), response.getBody().getId());
        verify(adicionarAlunoUseCase, times(1)).execute(alunoDTO);
    }

    @Test
    @DisplayName("POST /alunos - Should propagate conflict when email, rg or cpf already exists")
    void testAdicionarAlunoConflict() {
        AlunoDTO alunoDTO = criarAlunoDTO();

        doThrow(new DataConflictException("Conflito de email, rg ou cpf")).when(adicionarAlunoUseCase).execute(alunoDTO);

        Exception exception = assertThrows(DataConflictException.class, () -> controller.adicionarAluno(alunoDTO));

        assertEquals("Conflito de email, rg ou cpf", exception.getMessage());
        verify(adicionarAlunoUseCase, times(1)).execute(alunoDTO);
    }

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return 200 and paginated comprovantes")
    void testListarAlunosComComprovantesSuccess() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(null, null, null, "2025-01-01", "2025-12-31", 0, 10);
        AlunoComprovanteDTO dto1 = criarAlunoComprovanteDTO(1, "Joao Silva");
        AlunoComprovanteDTO dto2 = criarAlunoComprovanteDTO(2, "Maria Santos");

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of(dto1, dto2));
        when(mensalidadeRepository.countAlunosComMensalidade(
                filtro.status(),
                filtro.nome(),
                LocalDate.parse(filtro.dataEnvioFrom()),
                LocalDate.parse(filtro.dataEnvioTo())
        )).thenReturn(2L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response = controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().content().size());
    }

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return empty paginated response when no comprovantes found")
    void testListarAlunosComComprovantesEmpty() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(null, null, null, "2025-01-01", "2025-12-31", 0, 10);

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of());
        when(mensalidadeRepository.countAlunosComMensalidade(
                filtro.status(),
                filtro.nome(),
                LocalDate.parse(filtro.dataEnvioFrom()),
                LocalDate.parse(filtro.dataEnvioTo())
        )).thenReturn(0L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response = controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().content().isEmpty());
    }

    private Aluno criarAluno(Integer id, String nome, String email, String rg, String cpf) {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(Nome.of(nome));
        aluno.setEmail(Email.of(email));
        aluno.setRg(rg);
        aluno.setCpf(Cpf.of(cpf));
        aluno.setDataNascimento(DataNascimento.of(LocalDate.of(2000, 1, 1)));
        aluno.setDataInclusao(DataInclusao.of(LocalDateTime.now()));
        aluno.setResponsaveis(new ArrayList<>());
        aluno.setMensalidades(new ArrayList<>());
        return aluno;
    }

    private AlunoDTO criarAlunoDTO() {
        return new AlunoDTO(
                "Joao Silva",
                "joao.silva@example.com",
                LocalDate.of(2000, 1, 1),
                "58165708031",
                "123456789",
                null,
                "(11) 91234-5678",
                "(11) 3333-4444",
                "Masculino",
                "Brasileira",
                "Sao Paulo",
                "Estudante",
                null,
                true,
                false,
                true,
                null,
                new EnderecoDTO("Rua A", "10", "Centro", "Sao Paulo", "SP", "01001-000"),
                List.of()
        );
    }

    private AlunoComprovanteDTO criarAlunoComprovanteDTO(Integer id, String nome) {
        return new AlunoComprovanteDTO(
                1,
                id,
                nome,
                true,
                null,
                null,
                "PAGO",
                "CARTAO",
                null,
                false,
                false
        );
    }
}
