package br.com.vvatte.dao;

import br.com.vvatte.domain.Produto;

import java.util.List;

public interface IProdutoDAO {
    Integer cadastrar(Produto produto) throws Exception;

    Produto consultar(Long codigo) throws Exception;

    Integer excluir(Produto produtoDB) throws Exception;

    List<Produto> consultarTodos() throws Exception;

    Integer atualizar(Produto produtoBD) throws Exception;
}
