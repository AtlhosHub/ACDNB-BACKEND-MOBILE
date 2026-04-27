package com.teste.acdnb.infrastructure.persistence.jpa.censo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CensoRepository extends JpaRepository<CensoEntity, String> {
}
