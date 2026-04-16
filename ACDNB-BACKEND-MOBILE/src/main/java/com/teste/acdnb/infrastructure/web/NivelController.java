package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.nivel.ListarNiveisUseCase;
import com.teste.acdnb.core.domain.aluno.Nivel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nivel")
@SecurityRequirement(name = "Bearer")
@Tag(name = "NivelController", description = "Endpoints para gerenciar os niveis dos alunos no sistema")
public class NivelController {
    private final ListarNiveisUseCase listarNiveisUseCase;

    public NivelController(ListarNiveisUseCase listarNiveisUseCase){
        this.listarNiveisUseCase = listarNiveisUseCase;
    }


    @GetMapping
    public ResponseEntity<List<Nivel>> listarTodos(){
        List<Nivel> niveis = listarNiveisUseCase.execute();
        return ResponseEntity.ok(niveis);
    }
}
