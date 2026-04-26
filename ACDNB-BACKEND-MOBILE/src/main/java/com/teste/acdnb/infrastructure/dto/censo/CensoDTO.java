package com.teste.acdnb.infrastructure.dto.censo;

public record CensoDTO(
        String id,
        String nome_municipio,
        Float num_responsaveis,
        Float num_habitantes,
        Float var_num_habitantes,
        Float renda_media_responsavel,
        Float var_renda_responsavel,
        Float latitude,
        Float longitude
){}
