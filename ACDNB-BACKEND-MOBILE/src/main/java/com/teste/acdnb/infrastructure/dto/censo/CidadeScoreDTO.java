package com.teste.acdnb.infrastructure.dto.censo;

public record CidadeScoreDTO(

        String cidadeId,
        double rendaMedia,
        double populacao,
        double variacaoRenda,
        long interessados
) {}
