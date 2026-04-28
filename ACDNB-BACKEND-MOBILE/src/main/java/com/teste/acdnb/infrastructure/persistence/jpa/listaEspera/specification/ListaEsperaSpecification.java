package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera.specification;

import com.teste.acdnb.infrastructure.filter.InteressadosFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.listaEspera.ListaEsperaEntity;
import org.springframework.data.jpa.domain.Specification;

public class ListaEsperaSpecification {
    public static Specification<ListaEsperaEntity> filtrarPor(InteressadosFilter interessadosFilter){
        return hasNomeLike(interessadosFilter.nome());
    }

    private static Specification<ListaEsperaEntity> hasNomeLike(String nome) {
        return ((root, query, cb) -> nome == null || nome.isEmpty() ? cb.conjunction() : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
    }
}
