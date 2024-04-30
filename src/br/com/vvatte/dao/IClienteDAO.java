package br.com.vvatte.dao;

import br.com.vvatte.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {

    Integer cadastrar(Cliente cliente) throws Exception;

    Cliente consultar(Long cpf) throws  Exception;

    Integer excluir(Cliente clienteDB) throws  Exception;

    List<Cliente> consultarTodos() throws  Exception;;

    Integer atualizar(Cliente clienteBD)throws  Exception;
}
