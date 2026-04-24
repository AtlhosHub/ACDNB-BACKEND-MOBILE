package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCase;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/censo")
@SecurityRequirement(name = "Bearer")
public class CensoController {
    private final ImportarCensoUseCase importarCensoUseCase;

    public CensoController(ImportarCensoUseCase importarCensoUseCase) {
        this.importarCensoUseCase = importarCensoUseCase;
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarCenso(@RequestBody List<CensoDTO> dados_censo){
        importarCensoUseCase.execute(dados_censo);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/ranking")
//    public ResponseEntity<List<RankingDTO>> rankingCidades(){
//        return rankingCidadesUseCase.execute();
//    }
}
