package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.censo.ImportarCensoUseCase;
import com.teste.acdnb.core.application.usecase.censo.RankingCidadesUseCase;
import com.teste.acdnb.infrastructure.dto.censo.CensoDTO;
import com.teste.acdnb.infrastructure.dto.censo.RankingCidadesDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/censo")
@SecurityRequirement(name = "Bearer")
public class CensoController {
    private final ImportarCensoUseCase importarCensoUseCase;
    private final RankingCidadesUseCase rankingCidadesUseCase;

    public CensoController(ImportarCensoUseCase importarCensoUseCase, RankingCidadesUseCase rankingCidadesUseCase) {
        this.importarCensoUseCase = importarCensoUseCase;
        this.rankingCidadesUseCase = rankingCidadesUseCase;
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarCenso(@RequestBody List<CensoDTO> dados_censo){
        importarCensoUseCase.execute(dados_censo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingCidadesDTO>> rankingCidades(){
        return ResponseEntity.ok(rankingCidadesUseCase.execute());
    }
}
