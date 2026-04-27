package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.domain.shared.valueobject.Endereco;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ListaEsperaGateway {
    ListaEspera adicionarInteressado(ListaEspera listaEspera);
    List<ListaEspera> listarFiltro(InteressadosFilter interessadosFilter);
    List<ListaEspera> listarTodos();
    ListaEspera buscarPorId(int id);
    void deletarInteressado(int id);
    ListaEspera atualizarInteressado(ListaEspera listaEspera);
    Optional<Endereco> findEndereco(Endereco endereco);
    Endereco saveEndereco(Endereco endereco);
    Map<String, Long> interessadosPorCidade();
}
