package com.teste.acdnb.infrastructure.dto.censo;

public record RankingCidadesDTO(
        Integer rank,
        String nome,
        Double points,
        Float latitude,
        Float longitude
){}
