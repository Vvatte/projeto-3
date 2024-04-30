package br.com.vvatte.dao;

import br.com.vvatte.dao.jdbc.ConnectionFactory;
import br.com.vvatte.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    @Override
    public Integer cadastrar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_CLIENTE (ID, CPF, NOME, TEL, ENDERECO, NUMERO, CIDADE, ESTADO) VALUES (nextval('SQ_CLIENTE'),?,?,?,?,?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cliente.getCpf());
            stm.setString(2, cliente.getNome());
            stm.setLong(3, cliente.getTel());
            stm.setString(4, cliente.getEndereco());
            stm.setLong(5, cliente.getNumero());
            stm.setString(6, cliente.getCidade());
            stm.setString(7, cliente.getEstado());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(Long cpf) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from tb_cliente where cpf = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cpf);
            rs = stm.executeQuery();
            if(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setCpf(rs.getLong("cpf"));
                cliente.setNome(rs.getString("nome"));
            }
            return cliente;
        } catch (Exception e) {
            throw e;
        }
        finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Cliente clienteDB) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_CLIENTE WHERE CPF = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, clienteDB.getCpf());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList<>();
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_CLIENTE ";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                Long cpf = rs.getLong("CPF");
                Long tel = rs.getLong("TEL");
                String endereco = rs.getString("ENDERECO");
                Long numero = rs.getLong("NUMERO");
                String cidade = rs.getString("CIDADE");
                String estado = rs.getString("ESTADO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setTel(tel);
                cliente.setEndereco(endereco);
                cliente.setNumero(numero);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
                list.add(cliente);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer atualizar(Cliente clienteBD) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE TB_CLIENTE SET NOME = ?, CPF = ?, TEL = ?, ENDERECO = ?, NUMERO = ?, CIDADE = ?, ESTADO = ? WHERE ID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, clienteBD.getNome());
            stm.setLong(2, clienteBD.getCpf());
            stm.setLong(3, clienteBD.getTel());
            stm.setString(4, clienteBD.getEndereco());
            stm.setLong(5, clienteBD.getNumero());
            stm.setString(6, clienteBD.getCidade());
            stm.setString(7, clienteBD.getEstado());
            stm.setLong(8, clienteBD.getId());
            return stm.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
