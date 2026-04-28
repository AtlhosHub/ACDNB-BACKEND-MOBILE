package com.teste.acdnb.infrastructure.filter;

public record InteressadosFilter(
    String nome,
    Integer offset,
    Integer limit
) {
}
