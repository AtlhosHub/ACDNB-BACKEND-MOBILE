package com.teste.acdnb.infrastructure.persistence.jpa.nivel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NivelRepository extends JpaRepository<NivelEntity, Integer> {
}
