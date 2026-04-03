package com.teste.acdnb.infrastructure.persistence.jpa.observacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservacaoRepository extends JpaRepository<ObservacaoEntity, Integer> {
    List<ObservacaoEntity> findByAlunoId(int id);
}
