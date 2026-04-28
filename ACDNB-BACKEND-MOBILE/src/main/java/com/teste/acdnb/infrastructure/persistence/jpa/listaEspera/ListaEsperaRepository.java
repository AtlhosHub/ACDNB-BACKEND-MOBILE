package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ListaEsperaRepository extends JpaRepository<ListaEsperaEntity, Integer>, JpaSpecificationExecutor<ListaEsperaEntity> {
    Optional<ListaEsperaEntity> existsByEmailIgnoreCase(String email);
}
